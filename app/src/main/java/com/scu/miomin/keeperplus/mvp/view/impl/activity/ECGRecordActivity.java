package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.adapter.ECGRecordAdapter;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.presenter.impl.ECGRecordPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IECGRecordView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import butterknife.Bind;
import me.drakeet.materialdialog.MaterialDialog;


/**
 * 描述:病人端的健康档案 创建日期:2015/11/11
 *
 * @author 莫绪旻
 */
public class ECGRecordActivity extends BaseToolbarMvpActivity<ECGRecordPresenter> implements IECGRecordView {

    @Bind(R.id.lvecgrecord)
    ListView lvecgrecord;
    private static String uploadFilename = "";
    private MaterialDialog mMaterialDialog;

    @Override
    protected ECGRecordPresenter createPresenter() {
        return new ECGRecordPresenter(this);
    }

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_ecg_record, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        setUpTitle("心电云图库");
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mvpPresenter.initECGRecordAdapter();
        mvpPresenter.readEcgFileDir();

        // 设置点击事件监听器
        lvecgrecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uploadFilename = mvpPresenter.getClickItemFileName(position);

                mMaterialDialog = new MaterialDialog(ECGRecordActivity.this)
                        .setTitle("提示")
                        .setMessage("是否要将本次心电监测数据上传到云端？上传后可供医生查看")
                        .setPositiveButton("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                mvpPresenter.uploadECGFile(uploadFilename);
                            }
                        })
                        .setNegativeButton("否", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ECGRecordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setECGRecordAdapter(ECGRecordAdapter ecgRecordAdapter) {
        lvecgrecord.setAdapter(ecgRecordAdapter);
    }
}
