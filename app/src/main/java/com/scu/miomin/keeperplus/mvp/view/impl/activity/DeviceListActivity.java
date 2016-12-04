package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;

import java.util.Set;

/**
 * 描述:设备列表适配器 创建日期:2015/5/2
 *
 * @author 莫绪旻
 */
public class DeviceListActivity extends ToolbarActivity {

    // 调试TAG
    private static final String TAG = "DeviceListActivity";
    private static final boolean D = true;

    // 返回 Intent 数据
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    // 成员变量
    private BluetoothAdapter mBtAdapter;

    // 已配对适配器
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    // 未配对适配器
    private ArrayAdapter<String> mNewDevicesArrayAdapter;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_device_list, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        setUpTitle("设备管理");
        setResult(Activity.RESULT_CANCELED);

        // 初始化扫描按钮
        Button scanButton = (Button) findViewById(R.id.button_scan);
        scanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                doDiscovery();
                v.setVisibility(View.GONE);
            }
        });

        // 初始化蓝牙适配器
        mPairedDevicesArrayAdapter = new ArrayAdapter<>(this,
                R.layout.layout_device_name);
        mNewDevicesArrayAdapter = new ArrayAdapter<>(this,
                R.layout.layout_device_name);

        // 初始化已配对蓝牙设备的ListView
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);

        // 初始化未配对蓝牙设备的ListView
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);

        // 查找到蓝牙的广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // 查找完成的广播
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        // 获取蓝牙管理器
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // 获取已绑定的蓝牙设备
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // 添加已绑定的设备到适配器
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n"
                        + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired)
                    .toString();
            mPairedDevicesArrayAdapter.add(noDevices);
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DeviceListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 退出时释放资源
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        // 反注册广播
        this.unregisterReceiver(mReceiver);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    /**
     * BluetoothAdapter 开始查找蓝牙设备
     */
    private void doDiscovery() {
        if (D)
            Log.d(TAG, "doDiscovery()");

        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.connect);

        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        // 如果已经查找先停止
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        // 请求查找
        mBtAdapter.startDiscovery();
    }

    // 处理ListView的item点击事件
    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // 准备开始连接，取消查找
            mBtAdapter.cancelDiscovery();

            // 获取蓝牙设备的MAC地址
            String info = ((TextView) v).getText().toString();
            if (info.equals(getString(R.string.none_found)))
                return;
            if (info.equals(getString(R.string.none_paired)))
                return;
            String address = info.substring(info.length() - 17);

            // 把蓝牙设备的MAC地址通过Intent传递出去
            Intent intent = new Intent();
            intent.putExtra(EXTRA_DEVICE_ADDRESS, address);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

    // 蓝牙查找广播
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 找到一个蓝牙设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 从Intent获取蓝牙设备对象
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 跳过已配对的
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n"
                            + device.getAddress());
                }
                // 扫描完成的处理
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(
                            R.string.none_found).toString();
                    mNewDevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };
}
