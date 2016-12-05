package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.presenter.interf.IFollowUpLinePresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IFollowUpLineView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

/**
 * Created by miomin on 16/11/16.
 */

public class FollowUpLinePresenter extends BasePresenter<IFollowUpLineView> implements IFollowUpLinePresenter {

    public FollowUpLinePresenter(IFollowUpLineView iFollowUpLineView) {
        attachView(iFollowUpLineView);
    }
}
