package com.scu.miomin.keeperplus.mvp.view.interf;


import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface ITreatmentListPatientView extends IBaseView {

    void addTreatment(TreatmentBean treatmentBean);
}
