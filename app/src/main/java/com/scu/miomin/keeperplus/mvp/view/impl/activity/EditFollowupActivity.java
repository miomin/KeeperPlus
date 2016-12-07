package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.model.TreatmentFollowup;
import com.scu.miomin.keeperplus.mvp.presenter.impl.EditFollowupPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IEditFollowupView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import butterknife.Bind;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 描述:修改系统设置的界面 创建日期:2015/5/10
 *
 * @author 应均康
 */
public class EditFollowupActivity extends BaseToolbarMvpActivity<EditFollowupPresenter> implements IEditFollowupView {

    private static final String TREATMENT = "treatment";
    private TreatmentBean treatmentBean;

    @Bind(R.id.etRusuantuoqingmei)
    EditText etRusuantuoqingmei;
    @Bind(R.id.etgucaozhuananmei)
    EditText etgucaozhuananmei;
    @Bind(R.id.etgubingzhuananmei)
    EditText etgubingzhuananmei;
    @Bind(R.id.etlingsuanjisuanjimei)
    EditText etlingsuanjisuanjimei;
    @Bind(R.id.etlingsuanjisuanjimeitonggongmei)
    EditText etlingsuanjisuanjimeitonggongmei;
    @Bind(R.id.etjihongdanbai)
    EditText etjihongdanbai;
    @Bind(R.id.etjigaidanbai)
    EditText etjigaidanbai;
    @Bind(R.id.etWaizhouxuebaixibaozongshu)
    EditText etWaizhouxuebaixibaozongshu;
    @Bind(R.id.etzhongxinglixibaobilv)
    EditText etzhongxinglixibaobilv;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_edit_followup_info, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        setUpTitle("添加诊后随访记录");
    }

    public static void startActivity(Context context, TreatmentBean treatmentBean) {
        Intent intent = new Intent(context, EditFollowupActivity.class);
        intent.putExtra(TREATMENT, treatmentBean);
        context.startActivity(intent);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        treatmentBean = (TreatmentBean) getIntent().getSerializableExtra(TREATMENT);
        if (treatmentBean == null) {
            toast("数据加载失败");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_folloup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            saveFollowUp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void saveFollowUp() {
        TreatmentFollowup treatmentFollowup = new TreatmentFollowup("2016-12-08");
        treatmentFollowup.setTreatment(treatmentBean);

        if (!TextUtils.isEmpty(etRusuantuoqingmei.getText()))
            treatmentFollowup.setRUSUANTUOQINGMEI(Double.parseDouble(etRusuantuoqingmei.getText().toString()));
        if (!TextUtils.isEmpty(etgucaozhuananmei.getText()))
            treatmentFollowup.setGUCAOZHUANANMEI(Double.parseDouble(etgucaozhuananmei.getText().toString()));
        if (!TextUtils.isEmpty(etgubingzhuananmei.getText()))
            treatmentFollowup.setGUBINGZHUANANMEI(Double.parseDouble(etgubingzhuananmei.getText().toString()));
        if (!TextUtils.isEmpty(etlingsuanjisuanjimei.getText()))
            treatmentFollowup.setLINGSUANJISUANJIMEI(Double.parseDouble(etlingsuanjisuanjimei.getText().toString()));
        if (!TextUtils.isEmpty(etlingsuanjisuanjimeitonggongmei.getText()))
            treatmentFollowup.setLINGSUANJISUANJIMEITONGGONGMEI(Double.parseDouble(etlingsuanjisuanjimeitonggongmei.getText().toString()));
        if (!TextUtils.isEmpty(etjihongdanbai.getText()))
            treatmentFollowup.setJIHONGDANBAI(Double.parseDouble(etjihongdanbai.getText().toString()));
        if (!TextUtils.isEmpty(etjigaidanbai.getText()))
            treatmentFollowup.setJIGAIDANBAI(Double.parseDouble(etjigaidanbai.getText().toString()));
        if (!TextUtils.isEmpty(etWaizhouxuebaixibaozongshu.getText()))
            treatmentFollowup.setWAIZHOUXUEHONGXIBAOZONGSHU(Double.parseDouble(etWaizhouxuebaixibaozongshu.getText().toString()));
        if (!TextUtils.isEmpty(etzhongxinglixibaobilv.getText()))
            treatmentFollowup.setZHONGXINGLIXIBAOBILV(Double.parseDouble(etzhongxinglixibaobilv.getText().toString()));

        showLoading("请稍后", "正在保存数据...");
        treatmentFollowup.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
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
    protected EditFollowupPresenter createPresenter() {
        return new EditFollowupPresenter(this);
    }
}
