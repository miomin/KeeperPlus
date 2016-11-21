package com.scu.miomin.keeperplus.mvp.view.interf;


import android.content.SharedPreferences;

import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface ILoginView extends IBaseView {

    String getUserName();

    String getPassword();

    void setUserNameText(String userName);

    SharedPreferences getSharedPreferences(String spName);

    void startMainActivity();
}
