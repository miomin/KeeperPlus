package com.scu.miomin.keeperplus.mvp.view.interf;


import com.scu.miomin.keeperplus.mvp.model.TreatmentFollowup;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface ITreatmentInfoView extends IBaseView {

    void addTreatmentFollowup(TreatmentFollowup treatmentFollowup);
}
