package com.scu.miomin.keeperplus.mvp.view.interf;


import android.content.Context;

import com.scu.miomin.keeperplus.adapter.TreatmentListPatientAdapter;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface ITreatmentListPatientView extends IBaseView {

    Context getContext();

    void setTreatmentAdapter(TreatmentListPatientAdapter treatmentListPatientAdapter);
}
