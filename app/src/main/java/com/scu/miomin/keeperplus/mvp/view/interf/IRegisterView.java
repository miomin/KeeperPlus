package com.scu.miomin.keeperplus.mvp.view.interf;


import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface IRegisterView extends IBaseView {

    String getUserName();

    String getPassword();

    String getCode();

    void setCodeBtnEnable(boolean enable);
}
