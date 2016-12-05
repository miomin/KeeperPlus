
package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.presenter.impl.FollowUpLinePresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IFollowUpLineView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class FollowUpLineActivity extends BaseToolbarMvpActivity<FollowUpLinePresenter> implements IFollowUpLineView {

    private static final String TITLE_STR = "titleStr";
    private static final String DATAS = "datas";

    @Bind(R.id.trendLineChart)
    LineChart trendLineChart;
    @Bind(R.id.tvTitleSub)
    TextView tvTitleSub;

    private String titleStr;
    private ArrayList<Integer> datas;

    public static void startActivity(Context context, String titleStr, ArrayList<Integer> datas) {
        Intent intent = new Intent(context, FollowUpLineActivity.class);
        intent.putExtra(TITLE_STR, titleStr);
        intent.putExtra(DATAS, datas);
        context.startActivity(intent);
    }

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_followup_line, ActivityType.MODE_TOOLBAR_BACK);

        titleStr = getIntent().getStringExtra(TITLE_STR);
        datas = (ArrayList<Integer>) getIntent().getSerializableExtra(DATAS);

        if (titleStr == null || datas == null) {
            showToast("数据加载失败");
            finish();
            return;
        }
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        setUpTitle(titleStr);
        tvTitleSub.setText(titleStr);

        initChart();
        setLineChatData();
    }

    private void initChart() {
        // no description text
        trendLineChart.setDescription("");
        trendLineChart.setNoDataTextDescription("You need to provide data for the chart.");
        // enable touch gestures
        trendLineChart.setTouchEnabled(true);
        trendLineChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        trendLineChart.setDragEnabled(true);
        trendLineChart.setScaleEnabled(true);
        trendLineChart.setDrawGridBackground(false);
        trendLineChart.setHighlightPerDragEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        trendLineChart.setPinchZoom(true);
        // set an alternative background color
        trendLineChart.setBackgroundResource(R.color.white);
        trendLineChart.animateY(3000);

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        XAxis xAxis = trendLineChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(R.color.blackLight);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setSpaceBetweenLabels(1);

        YAxis leftAxis = trendLineChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaxValue(350);
        leftAxis.setDrawGridLines(true);
        leftAxis.setStartAtZero(true);

        YAxis rightAxis = trendLineChart.getAxisRight();
        rightAxis.setTypeface(tf);
        rightAxis.setTextColor(Color.WHITE);
        rightAxis.setAxisMaxValue(350);
        rightAxis.setDrawGridLines(false);
        leftAxis.setStartAtZero(true);
    }


    private void setLineChatData() {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < datas.size(); i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < datas.size(); i++) {
            yVals2.add(new Entry(datas.get(i), i));
        }

        // create a dataset and give it a type
        LineDataSet set2 = new LineDataSet(yVals2, titleStr);
        set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set2.setColor(getResources().getColor(R.color.colorPrimary));
        set2.setCircleColor(getResources().getColor(R.color.colorPrimary));
        set2.setLineWidth(2f);
        set2.setCircleSize(3f);
        set2.setFillAlpha(65);
        set2.setFillColor(getResources().getColor(R.color.colorPrimary));
        set2.setDrawCircleHole(false);
        set2.setHighLightColor(Color.rgb(244, 117, 117));
        set2.setDrawFilled(true);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set2);

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        data.setValueTextColor(R.color.blackLight);
        data.setValueTextSize(9f);

        // set data
        trendLineChart.setData(data);
    }

    @Override
    protected FollowUpLinePresenter createPresenter() {
        return new FollowUpLinePresenter(this);
    }
}
