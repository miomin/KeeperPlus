package com.scu.miomin.keeperplus.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.scu.miomin.keeperplus.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述:用于绘制心电图的自定义SurfaceView 创建日期:2015/5/10
 *
 * @author 莫绪旻
 */
public class ECGSurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {

    public static final int POINT_DIS = 1; // 点距离
    public static final int LINE_WIDE = 3; // 线的厚度

    private boolean isReady;
    private int tableSize = 30;
    private int distanceX = 0;
    private int distanceY = 0;
    private Paint mTablePaint;
    private Paint mLinePaint;

    int indexTemp = 0;

    private int[] dataDraw;
    private float pts[]; // 要画的数据

    public ECGSurfaceView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public ECGSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public ECGSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    // 初始化
    private void init() {
        this.setKeepScreenOn(true);

        mTablePaint = new Paint();
        mTablePaint.setColor(getResources().getColor(R.color.tablelinecolor));

        // 画笔属性
        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.ecglinecolor));
        mLinePaint.setStrokeWidth(LINE_WIDE);
        mLinePaint.setAntiAlias(true);// 表示抗锯齿
        mLinePaint.setDither(true);

        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        isReady = true;
        distanceX = getWidth();
        distanceY = getHeight() / 2;

        Canvas canvas = holder.lockCanvas();
        drawBg(canvas);
        holder.unlockCanvasAndPost(canvas);
    }

    // 画背景
    private void drawBg(Canvas canvas) {

        if (canvas != null) {

            canvas.drawColor(Color.WHITE);

            for (int i = 0; i <= distanceY; i = i + tableSize) {
                canvas.drawLine(0, distanceY - i, distanceX, distanceY - i,
                        mTablePaint);
                canvas.drawLine(0, distanceY + i, distanceX, distanceY + i,
                        mTablePaint);
            }
            for (int i = tableSize; i <= distanceX; i = i + tableSize) {
                canvas.drawLine(i, 0, i, distanceY * 2, mTablePaint);
            }

        }
    }

    // 实时画图
    public void realTimeDraw(int indexTemp, int[] dataCount, int counterAll,
                             int counterAllPre) {

        this.indexTemp = indexTemp;

        coordinateTrans(dataCount, counterAllPre);

        Rect rect = new Rect((counterAllPre - 1) * POINT_DIS, 0,
                (counterAll + 33) * POINT_DIS, getHeight());
        Canvas canvas = getHolder().lockCanvas(rect);// 特别注意的地方
        drawBg(canvas);
        canvas.drawLine((counterAllPre - 1) * POINT_DIS, this.indexTemp,
                counterAllPre * POINT_DIS, dataDraw[0], mLinePaint);

        // System.out.println((counterAllPre - 1) * POINT_DIS + ";"
        // + this.indexTemp + ";" + counterAllPre * POINT_DIS + ";"
        // + dataDraw[0]);
        canvas.drawLines(pts, mLinePaint);
        getHolder().unlockCanvasAndPost(canvas);

    }

    // 非实时画图
    public void timeDelayedDraw(int[] drawData) {

        if (drawData.length < 10)
            return;

        List<Integer> tmp = new ArrayList<Integer>();

        for (int i = 0; i < drawData.length; i++) {
            tmp.add(drawData[i]);
        }

        int maxnum = getWidth() / POINT_DIS;

        int count = 10;
        int[] data = new int[count];
        int counterAll = 0;
        int counterAllPre = 0;
        int last = tmp.isEmpty() ? 0 : tmp.get(0);

        while (!tmp.isEmpty()) {

            // 把list的count个数据取出来,dataCount为一个大小为count的数组
            for (int i = 0; i < count; i++) {
                if (tmp.size() == 0)
                    break;

                data[i] = tmp.get(0);
                tmp.remove(0);
            }
            // 统计总数
            counterAll += data.length;

            this.indexTemp = last;
            coordinateTrans(data, counterAllPre);
            Rect rect = new Rect((counterAllPre - 1) * POINT_DIS, 0,
                    (counterAll + 33) * POINT_DIS, getHeight());
            Canvas canvas = getHolder().lockCanvas(rect);// 特别注意的地方
            drawBg(canvas);
            canvas.drawLine((counterAllPre - 1) * POINT_DIS, this.indexTemp,
                    counterAllPre * POINT_DIS, dataDraw[0], mLinePaint);
            canvas.drawLines(pts, mLinePaint);
            getHolder().unlockCanvasAndPost(canvas);

            // 取得最后一个元素
            last = data[(data.length - 1)];

            if (counterAll == maxnum) {
                counterAll = 0;
                counterAllPre = 0;
                break;
            } else {
                counterAllPre += data.length;
            }

        }

    }

    // 非实时画图
    public void timeDelayedDraw(List<Integer> drawData) {

        if (drawData.size() < 10)
            return;

        int maxnum = getWidth() / POINT_DIS;

        int count = 10;
        int[] data = new int[count];
        int counterAll = 0;
        int counterAllPre = 0;
        int last = drawData.isEmpty() ? 0 : drawData.get(0);

        while (!drawData.isEmpty()) {

            // 把list的count个数据取出来,dataCount为一个大小为count的数组
            for (int i = 0; i < count; i++) {
                if (drawData.size() == 0)
                    break;

                data[i] = drawData.get(0);
                drawData.remove(0);
            }
            // 统计总数
            counterAll += data.length;

            this.indexTemp = last;
            coordinateTrans(data, counterAllPre);
            Rect rect = new Rect((counterAllPre - 1) * POINT_DIS, 0,
                    (counterAll + 33) * POINT_DIS, getHeight());
            Canvas canvas = getHolder().lockCanvas(rect);// 特别注意的地方
            drawBg(canvas);
            canvas.drawLine((counterAllPre - 1) * POINT_DIS, this.indexTemp,
                    counterAllPre * POINT_DIS, dataDraw[0], mLinePaint);
            canvas.drawLines(pts, mLinePaint);
            getHolder().unlockCanvasAndPost(canvas);

            // 取得最后一个元素
            last = data[(data.length - 1)];

            if (counterAll == maxnum) {
                counterAll = 0;
                counterAllPre = 0;
                break;
            } else {
                counterAllPre += data.length;
            }

        }

    }

    // 把ECG的数据转换为屏幕坐标
    public void coordinateTrans(int[] dataCount, int counterAllPre) {
        dataDraw = new int[dataCount.length];
        for (int i = 0; i < dataDraw.length; i++) {
            dataDraw[i] = getHeight() - (dataCount[i] * getHeight() / 4096);
        }

        this.indexTemp = getHeight() - (this.indexTemp * getHeight() / 4096);
        int j1 = -1;
        pts = new float[4 * (dataCount.length - 1)];
        for (int i = 0; i < pts.length; i++) {
            if (i % 4 == 0) {
                j1++;
                pts[i] = (counterAllPre + j1) * POINT_DIS;
            }
            if (i % 4 == 1)
                pts[i] = dataDraw[j1];
            if (i % 4 == 2)
                pts[i] = (counterAllPre + j1 + 1) * POINT_DIS;
            if (i % 4 == 3)
                pts[i] = dataDraw[j1 + 1];
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        isReady = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        isReady = false;
    }

    public boolean isReady() {
        return isReady;
    }

}