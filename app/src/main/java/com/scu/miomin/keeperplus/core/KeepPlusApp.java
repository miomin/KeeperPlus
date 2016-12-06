package com.scu.miomin.keeperplus.core;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.orhanobut.logger.Logger;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.APPAction;
import com.scu.miomin.keeperplus.constants.APPString;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.splash.SplashActivity;
import com.scu.miomin.keeperplus.string.LoginString;
import com.scu.miomin.keeperplus.util.ecg.ECGDirSaveUtil;
import com.scu.miomin.keeperplus.weex.adapter.PicassoImageAdapter;
import com.scu.miomin.keeperplus.weex.module.WXGetDoctorInfoModule;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;


/**
 * Created by 莫绪旻 and Stay on 2/2/16.
 */
public class KeepPlusApp extends Application {

    private static KeepPlusApp sInstance;
    public boolean AUTOLOGINSUCCEED = false;
    private static KeeperPlusCache keeperPlusCache;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // 初始化Weex引擎
        InitConfig config = new InitConfig.Builder().setImgAdapter(new PicassoImageAdapter()).build();
        WXSDKEngine.initialize(this, config);
        try {
            WXSDKEngine.registerModule("getuserinfo", WXGetDoctorInfoModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }

        Fresco.initialize(this);
        registerAppController();
        Logger.init(APPString.TAG).methodCount(2);
        NIMClient.init(this, null, options()); // SDK初始化（启动后台服务，若已经存在用户登录信息，SDK 将完成自动登录）
        // 创建心电图目录文件
        ECGDirSaveUtil.creatDirFile(this);
        keeperPlusCache = KeeperPlusCache.getInstance();
        initMoke();
        initBmob();
    }

    private void initBmob() {
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("cab9d9865d9fd7a560e90659ae25b73c")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(10)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }

    private void initMoke() {
//        KeeperDataMoke.getInstance().initData();
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

    public static KeepPlusApp getInstance() {
        return sInstance;
    }

    // 如果返回值为 null，则全部使用默认参数。
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = SplashActivity.class;
        config.notificationSmallIconId = R.mipmap.ic_launcher;
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim";
        options.sdkStorageRootPath = sdkPath;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小，该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = 480 / 2;

        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
        options.userInfoProvider = new UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String account) {
                return null;
            }

            @Override
            public Bitmap getAvatarForMessageNotifier(String account) {
                return null;
            }

            @Override
            public String getDisplayNameForMessageNotifier(String s, String s1, SessionTypeEnum sessionTypeEnum) {
                return null;
            }

            @Override
            public int getDefaultIconResId() {
                return R.drawable.img_default;
            }

            @Override
            public Bitmap getTeamIcon(String tid) {
                return null;
            }
        };

        return options;
    }

    // 获取之前的登录信息
    private LoginInfo getLoginInfo() {

        SharedPreferences sp = getSharedPreferences(LoginString.LOGININFO, MODE_PRIVATE);

        // 从本地读取上次登录成功时保存的用户登录信息
        String phonenumber = sp.getString(LoginString.PHONENUMBER, null);
        String password = sp.getString(LoginString.PASSWORD, null);

        if (!TextUtils.isEmpty(phonenumber) && !TextUtils.isEmpty(password)) {
            return null;
        } else {
            return null;
        }
    }
}

