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
import com.scu.miomin.keeperplus.adapter.TreatmentFollowupAdapter;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.presenter.impl.TreatmentInfoPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentInfoView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import butterknife.Bind;

/**
 * 描述:绘制心电图的界面 创建日期:2015/5/23
 *
 * @author 莫绪旻
 */
public class TreatmentInfoActivity extends BaseToolbarMvpActivity<TreatmentInfoPresenter> implements ITreatmentInfoView {

    @Bind(R.id.lvTreatmentFollowup)
    ListView lvTreatmentFollowup;

    private static final String TREATMENT = "treatment";

    private TreatmentBean treatmentBean;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_treatment_info, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        setUpTitle("就诊记录详情");

        lvTreatmentFollowup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    return;
                FollowupInfoActivity.startActivity(TreatmentInfoActivity.this, treatmentBean.getTreatmentFollowupList().get(position - 1),
                        treatmentBean);
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        treatmentBean = (TreatmentBean) intent.getSerializableExtra(TREATMENT);

        if (treatmentBean == null) {
            showToast("加载信息失败");
            finish();
            return;
        }

        initAdapter();
    }

    private void initAdapter() {
        mvpPresenter.initTreatmentFollowUpAdapter(treatmentBean);
    }

    public static void startActivity(Context context, TreatmentBean treatmentBean) {
        Intent intent = new Intent(context, TreatmentInfoActivity.class);
        intent.putExtra(TREATMENT, treatmentBean);
        context.startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setTreatmentInfoAdapter(TreatmentFollowupAdapter treatmentInfoAdapter) {
        lvTreatmentFollowup.setAdapter(treatmentInfoAdapter);
    }

    @Override
    protected TreatmentInfoPresenter createPresenter() {
        return new TreatmentInfoPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_treatment_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_followup) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
