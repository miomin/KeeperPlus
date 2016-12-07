package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.presenter.interf.IEditTreatmentPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IEditTreatmentView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

/**
 * Created by miomin on 16/11/16.
 */

public class EditTreatmentPresenter extends BasePresenter<IEditTreatmentView> implements IEditTreatmentPresenter {

    public EditTreatmentPresenter(IEditTreatmentView iEditTreatmentView) {
        attachView(iEditTreatmentView);
    }
}
