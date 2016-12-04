package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.adapter.TreatmentListPatientAdapter;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.presenter.impl.TreatmentListPatientPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentListPatientView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import butterknife.Bind;


/**
 * 描述:病人端诊后随访界面 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentListPatientActivity extends BaseToolbarMvpActivity<TreatmentListPatientPresenter> implements ITreatmentListPatientView {

    @Bind(R.id.lvTreatment)
    ListView lvTreatmentFollowupList;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_treatment_patient_list, ActivityType.MODE_TOOLBAR_BACK);
        mvpPresenter.initTreatmentData();
    }

    @Override
    protected TreatmentListPatientPresenter createPresenter() {
        return new TreatmentListPatientPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter.clearTreatmentData();
    }

    @Override
    protected void setUpView() {

//        lvTreatmentFollowupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TreatmentInfoActivityForPatient.actionStart(TreatmentListActivityForPatient.this,
//                        UserResource.getTreatmentArray().get(position - 1));
//            }
//        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mvpPresenter.initTreatmentAdapter();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TreatmentListPatientActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setTreatmentAdapter(TreatmentListPatientAdapter treatmentListPatientAdapter) {
        lvTreatmentFollowupList.setAdapter(treatmentListPatientAdapter);
    }
}
