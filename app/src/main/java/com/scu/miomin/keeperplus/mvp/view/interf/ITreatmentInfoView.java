package com.scu.miomin.keeperplus.mvp.view.interf;


import android.content.Context;

import com.scu.miomin.keeperplus.adapter.TreatmentFollowupAdapter;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface ITreatmentInfoView extends IBaseView {

    Context getContext();

    void setTreatmentInfoAdapter(TreatmentFollowupAdapter treatmentInfoAdapter);
}
