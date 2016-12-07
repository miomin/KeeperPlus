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
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.ILoginPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ILoginView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;
import com.scu.miomin.keeperplus.string.APPString;
import com.scu.miomin.keeperplus.string.LoginString;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by miomin on 16/11/16.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    public LoginPresenter(ILoginView iLoginView) {
        attachView(iLoginView);
    }

    @Override
    public void login() {

        mvpView.showLoading("提示", "登录中，请稍后");
        LoginInfo loginInfo = new LoginInfo(mvpView.getUserName(), mvpView.getPassword());

        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(final LoginInfo loginInfo) {

                        BmobQuery<Userbean> query = new BmobQuery<>();
                        query.addWhereEqualTo("mobilePhoneNumber", loginInfo.getAccount());
                        query.include("birthday");
                        query.include("hospital");
                        query.findObjects(new FindListener<Userbean>() {
                            @Override
                            public void done(List<Userbean> list, BmobException e) {
                                if (e == null) {
                                    if (list.size() > 0) {
                                        KeeperPlusCache.getInstance().setCurrentUser(list.get(0));
                                        // 保存登录信息到SharedPerences
                                        saveLoginInfo(loginInfo);
                                        mvpView.showToast(KeepPlusApp.getInstance().getResources().getString(R.string.loginsucceed));
                                        mvpView.startMainActivity();
                                    }
                                } else {
                                    mvpView.showToast("对不起，您的网络不太稳定...");
                                }
                                mvpView.hideLoading();
                            }
                        });
                    }

                    @Override
                    public void onFailed(int i) {
                        mvpView.showToast(KeepPlusApp.getInstance().getResources().getString(R.string.loginfaild));
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        mvpView.showToast(KeepPlusApp.getInstance().getResources().getString(R.string.loginfaild));
                        mvpView.hideLoading();
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
