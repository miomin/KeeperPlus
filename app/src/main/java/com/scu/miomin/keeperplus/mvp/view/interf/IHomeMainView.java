package com.scu.miomin.keeperplus.mvp.view.interf;


import android.app.Activity;

import com.scu.miomin.keeperplus.adapter.RemenDoctorAdapter;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface IHomeMainView extends IBaseView {

    void setRemenDoctorAdapter(RemenDoctorAdapter remenDoctorAdapter);

    Activity getViewActivity();
}
