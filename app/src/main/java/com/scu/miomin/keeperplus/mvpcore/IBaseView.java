package com.scu.miomin.keeperplus.mvpcore;

/**
 * Created by miomin on 2016/11/14.
 */
public interface IBaseView {

    void showLoading(String title, String message);

    void hideLoading();

    void showToast(String message);
}
