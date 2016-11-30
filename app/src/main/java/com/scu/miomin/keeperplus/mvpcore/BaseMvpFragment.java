package com.scu.miomin.keeperplus.mvpcore;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scu.miomin.keeperplus.core.BaseFragment;


/**
 * Created by miomin on 2016/11/21
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IBaseView {

    protected P mvpPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    @Override
    public void showLoading(String title, String message) {
        if (getActivity() instanceof BaseToolbarMvpActivity)
            ((BaseToolbarMvpActivity) getActivity()).showLoading(title, message);
    }

    @Override
    public void hideLoading() {
        if (getActivity() instanceof BaseToolbarMvpActivity)
            ((BaseToolbarMvpActivity) getActivity()).hideLoading();
    }

    @Override
    public void showToast(String message) {
        if (getActivity() instanceof BaseToolbarMvpActivity)
            ((BaseToolbarMvpActivity) getActivity()).showToast(message);
    }
}
