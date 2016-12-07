package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;

import butterknife.OnClick;

/**
 * Created by miomin on 15/11/13.
 */
public class DoctorInfoActivity2 extends ToolbarActivity {

    SimpleDraweeView ivDoctorHead;
    TextView doctorName, tvProfessional, tvAdministrative, tvHospital, tvIntroduction;

    Userbean doctorBean;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_doctor_info, ActivityType.MODE_TOOLBAR_BACK);
        doctorBean = (Userbean) getIntent().getSerializableExtra("doctorBean");
    }

    @Override
    protected void setUpView() {
        ivDoctorHead = (SimpleDraweeView) findViewById(R.id.ivDoctorHead);
        doctorName = (TextView) findViewById(R.id.doctorName);
        tvProfessional = (TextView) findViewById(R.id.tvProfessional);
        tvAdministrative = (TextView) findViewById(R.id.tvAdministrative);
        tvHospital = (TextView) findViewById(R.id.tvHospital);
        tvIntroduction = (TextView) findViewById(R.id.tvIntroduction);
    }

    public static void startActivity(Context context, Userbean doctorBean) {
        Intent intent = new Intent(context, DoctorInfoActivity2.class);
        intent.putExtra("doctorBean", doctorBean);
        context.startActivity(intent);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        doctorName.setText(doctorBean.getUsername());
        tvProfessional.setText(doctorBean.getProfessional());
        tvAdministrative.setText(doctorBean.getAdministrative());
        tvHospital.setText(doctorBean.getHospital().getName());
        tvIntroduction.setText(doctorBean.getIntroduction());
        ivDoctorHead.setImageURI(Uri.parse(doctorBean.getHeadUrl()));
    }

    @OnClick(R.id.btn_openchat)
    public void openchat() {
        if (doctorBean != null && !TextUtils.isEmpty(doctorBean.getMobilePhoneNumber()))
            ChatActivity.startActivity(DoctorInfoActivity2.this, doctorBean.getMobilePhoneNumber());
    }
}
