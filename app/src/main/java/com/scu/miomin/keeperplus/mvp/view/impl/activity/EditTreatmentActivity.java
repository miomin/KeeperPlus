package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.presenter.impl.EditTreatmentPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IEditTreatmentView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 描述:修改系统设置的界面 创建日期:2015/5/10
 *
 * @author 应均康
 */
public class EditTreatmentActivity extends BaseToolbarMvpActivity<EditTreatmentPresenter> implements IEditTreatmentView, DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private DateFormat dateFormat;

    private Userbean doctor;

    @Bind(R.id.ivDoctorHead)
    SimpleDraweeView ivDoctorHead;
    @Bind(R.id.tvDoctorName)
    TextView tvDoctorName;
    @Bind(R.id.tvAdministrative)
    TextView tvAdministrative;
    @Bind(R.id.tvProfessional)
    TextView tvProfessional;
    @Bind(R.id.tvTreatmentDate)
    TextView tvTreatmentDate;
    @Bind(R.id.tvTreatmentHospital)
    EditText tvTreatmentHospital;
    @Bind(R.id.tvTreatmentReason)
    EditText tvTreatmentReason;
    @Bind(R.id.ed_find_userphone)
    EditText ed_find_userphone;
    @Bind(R.id.tvPatientName)
    TextView tvPatientName;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_edit_treatment_info, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        setUpTitle("添加就诊记录");
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EditTreatmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        tvPatientName.setText(KeeperPlusCache.getInstance().getCurrentUser().getUsername());
    }

    @OnClick(R.id.tvTreatmentDate)
    public void selectDate() {
        DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
    }

    @OnClick(R.id.btn_find_doctor)
    public void selectDoctor() {
        if (ed_find_userphone == null || ed_find_userphone.getText().toString() == null) {
            return;
        }

        String phonenumber = ed_find_userphone.getText().toString();
        findDoctor(phonenumber);
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        String date = dateFormat.format(calendar.getTime());
        tvTreatmentDate.setText(date);
    }

    private void findDoctor(String userphone) {
        showLoading("请稍后", "正在查找");
        BmobQuery<Userbean> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", userphone);
        query.include("birthday");
        query.include("hospital");
        query.findObjects(new FindListener<Userbean>() {
            @Override
            public void done(List<Userbean> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        doctor = list.get(0);
                        if (doctor == null)
                            return;
                        ivDoctorHead.setImageURI(Uri.parse(doctor.getHeadUrl()));
                        tvDoctorName.setText(doctor.getUsername());
                        tvAdministrative.setText(doctor.getAdministrative());
                        tvProfessional.setText(doctor.getProfessional());
                        tvTreatmentHospital.setText(doctor.getHospital().getName());
                    } else {
                        toast("对不起，没有查找到任何结果...");
                    }
                } else {
                    toast("对不起，您的网络不太稳定...");
                }
                hideLoading();
            }
        });
    }

    @Override
    protected EditTreatmentPresenter createPresenter() {
        return new EditTreatmentPresenter(this);
    }

    private void saveTreatment() {
        if (doctor == null) {
            toast("请选择医生");
            return;
        }
        if (TextUtils.isEmpty(tvTreatmentDate.getText())) {
            toast("请选择就诊日期");
            return;
        }
        if (TextUtils.isEmpty(tvTreatmentReason.getText())) {
            toast("请输入病情诊断");
            return;
        }
        showLoading("请稍后", "正在保存数据");

        TreatmentBean treatment = new TreatmentBean(tvTreatmentDate.getText().toString()
                , doctor, KeeperPlusCache.getInstance().getCurrentUser(),
                tvTreatmentReason.getText().toString());
        treatment.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    toast("创建数据成功");
                    finish();
                } else {
                    toast("对不起，您的网络不太稳定...");
                }
                hideLoading();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_treatment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            saveTreatment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
