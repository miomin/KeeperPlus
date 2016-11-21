package com.scu.miomin.keeperplus.mvp.presenter.impl;

import android.content.SharedPreferences;
import android.util.Log;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.core.KeepPlusApp;
import com.scu.miomin.keeperplus.moke.UserMoke;
import com.scu.miomin.keeperplus.mvp.cache.CurrentUserCache;
import com.scu.miomin.keeperplus.mvp.module.DoctorBean;
import com.scu.miomin.keeperplus.mvp.module.PatientBean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.ILoginPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ILoginView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;
import com.scu.miomin.keeperplus.string.APPString;
import com.scu.miomin.keeperplus.string.LoginString;

/**
 * Created by miomin on 16/11/16.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    public LoginPresenter(ILoginView iLoginView) {
        attachView(iLoginView);
    }

    @Override
    public void login() {

        LoginInfo loginInfo = new LoginInfo(mvpView.getUserName(), mvpView.getPassword());

        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {

                        if (loginInfo.getAccount().length() == 11) {
                            PatientBean patientBean = (PatientBean) UserMoke.getInstance().getUserByID(loginInfo.getAccount());
                            CurrentUserCache.getInstance().setCurrentUser(patientBean);
                        } else {
                            DoctorBean doctorBean = (DoctorBean) UserMoke.getInstance().getUserByID(loginInfo.getAccount());
                            CurrentUserCache.getInstance().setCurrentUser(doctorBean);
                        }

                        mvpView.startMainActivity();

                        // 保存登录信息到SharedPerences
                        saveLoginInfo(loginInfo);

                        mvpView.showToast(KeepPlusApp.getInstance().getResources().getString(R.string.loginsucceed));
                    }

                    @Override
                    public void onFailed(int i) {
                        mvpView.showToast(KeepPlusApp.getInstance().getResources().getString(R.string.loginfaild));
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        mvpView.showToast(KeepPlusApp.getInstance().getResources().getString(R.string.loginfaild));
                    }
                };

        NIMClient.getService(AuthService.class).login(loginInfo)
                .setCallback(callback);

        NIMClient.getService(AuthServiceObserver.class).observeLoginSyncDataStatus(new Observer<LoginSyncStatus>() {
            @Override
            public void onEvent(LoginSyncStatus status) {
                if (status == LoginSyncStatus.BEGIN_SYNC) {
                    Log.i(APPString.TAG, "login sync data begin");
                } else if (status == LoginSyncStatus.SYNC_COMPLETED) {
                    Log.i(APPString.TAG, "login sync data completed");
                }
            }
        }, true);
    }

    @Override
    public void scanLastLogininfo() {

        SharedPreferences sp = mvpView.getSharedPreferences(LoginString.LOGININFO);

        if (sp != null) {
            // 从本地读取上次登录成功时保存的用户登录信息
            String phonenumber = sp.getString(LoginString.PHONENUMBER, null);
            if (phonenumber != null) {
                mvpView.setUserNameText(phonenumber);
            }
        }
    }


    // 保存登录信息到SharedPerences
    public void saveLoginInfo(LoginInfo info) {
        SharedPreferences sp = mvpView.getSharedPreferences(LoginString.LOGININFO);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(LoginString.PHONENUMBER, info.getAccount());
        editor.putString(LoginString.PASSWORD, info.getToken());
        editor.commit();
    }
}
