package com.scu.miomin.keeperplus.core;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.scu.miomin.keeperplus.constants.APPStatu;
import com.scu.miomin.keeperplus.constants.APPString;
import com.scu.miomin.keeperplus.util.progress.PDMessage;
import com.scu.miomin.keeperplus.util.progress.ProgressDialogHandler;

import butterknife.ButterKnife;


/**
 * Created by 莫绪旻 and Stay on 2/2/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ProgressDialogHandler mProgressDialogHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(APPString.TAG, getClass().getSimpleName() + " oncreate");
        super.onCreate(savedInstanceState);
        getContentView();
        ButterKnife.bind(this);
        // 添加一个Activity实例到AppStatusTracker
        AppStatusTracker.getInstance(getApplication()).addActivity(this);
        switch (AppStatusTracker.getInstance(getApplication()).getAppStatus()) {
            // 若果App从后台恢复，且被kill掉
            case APPStatu.STATUS_FORCE_KILLED:
                Log.i(APPString.TAG, "protectApp");
                protectApp();
                break;
            // 如果APP正常启动
            case APPStatu.STATUS_ONLINE:
                setUpView();
                setUpData(savedInstanceState);
                break;
        }

        mProgressDialogHandler = new ProgressDialogHandler(this, false);
    }

    protected abstract void getContentView();

    protected abstract void setUpView();

    @Override
    protected void onDestroy() {
        Log.i(APPString.TAG, getClass().getSimpleName() + " destory");
        super.onDestroy();
        // 从AppStatusTracker中删除被销毁的activity实例
        AppStatusTracker.getInstance(getApplication()).removeActivity(this);
        mProgressDialogHandler = null;
    }

    @Override
    protected void onPause() {
        dismissProgressDialog();
        super.onPause();
    }

    /**
     * 正常启动
     */
    protected abstract void setUpData(Bundle savedInstanceState);

    /**
     * 如果App被kill掉了，应该回到welcomeActivity（singtask），重新进入APP正常的启动流程
     */
    protected void protectApp() {
        AppStatusTracker.getInstance(getApplication()).protectApp(this);
    }

    public void showProgressDialog(String title, String message) {
        if (mProgressDialogHandler != null) {
            Message msg = mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG);
            PDMessage pdmessage = new PDMessage(title, message);
            msg.obj = pdmessage;
            msg.sendToTarget();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        }
    }

//    // 监听到返回键时，退出应用
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            AppStatusTracker.getInstance(getApplication()).finishAll();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public void toast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            dismissProgressDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
