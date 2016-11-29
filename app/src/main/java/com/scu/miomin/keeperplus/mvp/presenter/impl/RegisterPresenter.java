package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.presenter.interf.IRegisterPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IRegisterView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

/**
 * Created by miomin on 16/11/16.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter {

    public RegisterPresenter(IRegisterView iRegisterView) {
        attachView(iRegisterView);
    }

    @Override
    public void register() {
        String code = mvpView.getCode();
        String userphone = mvpView.getUserName();
        String password = mvpView.getPassword();
        String username = userphone;
    }

    @Override
    public void getTelCode() {
        mvpView.setCodeBtnEnable(false);
    }
}
