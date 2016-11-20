package com.scu.miomin.keeperplus.core;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;
import com.scu.miomin.keeperplus.constants.APPAction;
import com.scu.miomin.keeperplus.constants.APPString;
import com.scu.miomin.keeperplus.splash.SplashActivity;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by 莫绪旻 and Stay on 2/2/16.
 */
public class ShareApplication extends Application {

    private static ShareApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Fresco.initialize(this);
        LeakCanary.install(this);
        registerAppController();
        Logger.init(APPString.TAG).methodCount(2);
    }

    private void registerAppController() {
        AppStatusTracker.getInstance(this).registerAppController(new AppController() {
            /**
             * 如果App被kill掉了，应该回到welcomeActivity（singtask），重新进入APP正常的启动流程
             */
            @Override
            public void protectApp(Context context) {
                Intent intent = new Intent(context, SplashActivity.class);
                intent.putExtra(APPAction.KEY_HOME_ACTION, APPAction.ACTION_RESTART_APP);
                startActivity(intent);
            }
        });
    }

    public static ShareApplication getInstance() {
        return sInstance;
    }
}

