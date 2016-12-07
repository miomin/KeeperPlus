package com.scu.miomin.keeperplus.mvpcore;

import android.os.Bundle;

import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;


/**
 * Created by miomin on 2016/11/14.
 */
public abstract class BaseToolbarMvpActivity<P extends BasePresenter> extends ToolbarActivity implements IBaseView {

    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    @Override
    public void showLoading(String title, String message) {
        showProgressDialog(title, message);
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showToast(String message) {
        toast(message);
    }
}
