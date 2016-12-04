package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.model.TreatmentFollowupBean;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 描述:绘制心电图的界面 创建日期:2015/5/23
 *
 * @author 莫绪旻
 */
public class FollowupInfoActivity extends ToolbarActivity {

    private TreatmentFollowupBean treatmentFollowupBean;
    private TreatmentBean treatmentBean;
    private ArrayList<Integer> datas = new ArrayList<>();

    @Bind(R.id.tvRusuantuoqingmei)
    TextView tvRusuantuoqingmei;
    @Bind(R.id.tvgucaozhuananmei)
    TextView tvgucaozhuananmei;
    @Bind(R.id.tvgubingzhuananmei)
    TextView tvgubingzhuananmei;
    @Bind(R.id.tvlingsuanjisuanjimei)
    TextView tvlingsuanjisuanjimei;
    @Bind(R.id.tvlingsuanjisuanjimeitonggongmei)
    TextView tvlingsuanjisuanjimeitonggongmei;
    @Bind(R.id.tvjihongdanbai)
    TextView tvjihongdanbai;
    @Bind(R.id.tvjigaidanbai)
    TextView tvjigaidanbai;
    @Bind(R.id.tvWaizhouxuebaixibaozongshu)
    TextView tvWaizhouxuebaixibaozongshu;
    @Bind(R.id.tvzhongxinglixibaobilv)
    TextView tvzhongxinglixibaobilv;

    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    @Bind(R.id.tv5)
    TextView tv5;
    @Bind(R.id.tv6)
    TextView tv6;
    @Bind(R.id.tv7)
    TextView tv7;
    @Bind(R.id.tv8)
    TextView tv8;
    @Bind(R.id.tv9)
    TextView tv9;

    @Bind(R.id.tvFllowupdata)
    TextView tvFllowupdata;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_followup_info, ActivityType.MODE_TOOLBAR_BACK);

        treatmentFollowupBean = (TreatmentFollowupBean) getIntent().getSerializableExtra("treatmentFollowupBean");
        treatmentBean = (TreatmentBean) getIntent().getSerializableExtra("treatmentBean");

        if (treatmentFollowupBean == null || treatmentBean == null) {
            showToast("数据加载失败");
            finish();
            return;
        }
    }

    @Override
    protected void setUpView() {
        setUpTitle("诊后随访详情");
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        tvRusuantuoqingmei.setText("" + treatmentFollowupBean.getRUSUANTUOQINGMEI());
        tvgucaozhuananmei.setText("" + treatmentFollowupBean.getGUCAOZHUANANMEI());
        tvgubingzhuananmei.setText("" + treatmentFollowupBean.getGUBINGZHUANANMEI());
        tvlingsuanjisuanjimei.setText("" + treatmentFollowupBean.getLINGSUANJISUANJIMEI());
        tvlingsuanjisuanjimeitonggongmei.setText("" + treatmentFollowupBean.getLINGSUANJISUANJIMEITONGGONGMEI());
        tvjihongdanbai.setText("" + treatmentFollowupBean.getJIHONGDANBAI());
        tvjigaidanbai.setText("" + treatmentFollowupBean.getJIGAIDANBAI());
        tvWaizhouxuebaixibaozongshu.setText("" + treatmentFollowupBean.getWAIZHOUXUEHONGXIBAOZONGSHU());
        tvzhongxinglixibaobilv.setText("" + treatmentFollowupBean.getZHONGXINGLIXIBAOBILV());
        tvFllowupdata.setText(treatmentFollowupBean.getDate());
    }

    public static void startActivity(Context context, TreatmentFollowupBean treatmentFollowupBean,
                                     TreatmentBean treatmentBean) {
        Intent intent = new Intent(context, FollowupInfoActivity.class);
        intent.putExtra("treatmentFollowupBean", treatmentFollowupBean);
        intent.putExtra("treatmentBean", treatmentBean);
        context.startActivity(intent);
    }

    public void checkRusuantuoqingmei(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getRUSUANTUOQINGMEI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv1.getText().toString(), datas);
        datas.clear();
    }

    public void checkGucaozhuananmei(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getGUCAOZHUANANMEI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv2.getText().toString(), datas);
        datas.clear();
    }

    public void checkGubingzhuananmei(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getGUBINGZHUANANMEI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv3.getText().toString(), datas);
        datas.clear();
    }

    public void checkLingsuanjisuanjimei(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getLINGSUANJISUANJIMEI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv4.getText().toString(), datas);
        datas.clear();
    }

    public void checkLingsuanjisuanjimeitonggongmei(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getLINGSUANJISUANJIMEITONGGONGMEI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv5.getText().toString(), datas);
        datas.clear();
    }

    public void checkJihongdanbai(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getJIHONGDANBAI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv6.getText().toString(), datas);
        datas.clear();
    }

    public void checkJigaidanbai(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getJIGAIDANBAI());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv7.getText().toString(), datas);
        datas.clear();
    }

    public void checkWaizhouxuebaixibaozongshu(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getWAIZHOUXUEHONGXIBAOZONGSHU());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv8.getText().toString(), datas);
        datas.clear();
    }

    public void checkZhongxinglixibaibilv(View view) {
        for (int i = treatmentBean.getTreatmentFollowupList().size() - 1; i >= 0; i--) {
            datas.add((int) treatmentBean.getTreatmentFollowupList().get(i).getZHONGXINGLIXIBAOBILV());
            if (treatmentBean.getTreatmentFollowupList().size() - i == 7)
                break;
        }

        FollowUpLineActivity.startActivity(this, tv9.getText().toString(), datas);
        datas.clear();
    }
}
