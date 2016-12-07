package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.presenter.interf.IEditFollowupPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IEditFollowupView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

/**
 * Created by miomin on 16/11/16.
 */

public class EditFollowupPresenter extends BasePresenter<IEditFollowupView> implements IEditFollowupPresenter {

    public EditFollowupPresenter(IEditFollowupView iEditFollowupView) {
        attachView(iEditFollowupView);
    }
}
