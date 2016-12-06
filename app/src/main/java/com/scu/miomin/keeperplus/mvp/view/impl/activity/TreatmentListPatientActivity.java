package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.adapter.TreatmentListPatientAdapter;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.presenter.impl.TreatmentListPatientPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentListPatientView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 描述:病人端诊后随访界面 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentListPatientActivity extends BaseToolbarMvpActivity<TreatmentListPatientPresenter> implements ITreatmentListPatientView {

    @Bind(R.id.lvTreatment)
    ListView lvTreatmentFollowupList;

    private ArrayList<TreatmentBean> treatmentBeanList = new ArrayList<>();
    private TreatmentListPatientAdapter treatmentListPatientAdapter;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_treatment_patient_list, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected TreatmentListPatientPresenter createPresenter() {
        return new TreatmentListPatientPresenter(this);
    }

    @Override
    protected void onDestroy() {
        treatmentListPatientAdapter = null;
        treatmentBeanList.clear();
        treatmentBeanList = null;
        super.onDestroy();
    }

    @Override
    protected void setUpView() {
        setUpTitle("就诊记录列表");

        lvTreatmentFollowupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreatmentInfoActivity.startActivity(TreatmentListPatientActivity.this, treatmentBeanList.get(position));
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        // 创建适配器对象
        treatmentListPatientAdapter = new TreatmentListPatientAdapter(this, treatmentBeanList);
        // 将ListView与适配器关联
        lvTreatmentFollowupList.setAdapter(treatmentListPatientAdapter);

        mvpPresenter.initTreatmentData();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TreatmentListPatientActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void addTreatment(TreatmentBean treatmentBean) {
        treatmentBeanList.add(treatmentBean);
        treatmentListPatientAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_treatment_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_treatment) {
            EditTreatmentActivity.startActivity(TreatmentListPatientActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
