package com.scu.miomin.keeperplus.mvp.view.interf;


import android.content.Context;

import com.scu.miomin.keeperplus.adapter.ECGRecordAdapter;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface IECGRecordView extends IBaseView {

    Context getContext();

    void setECGRecordAdapter(ECGRecordAdapter ecgRecordAdapter);
}
