package com.scu.miomin.keeperplus.util.ecg;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 描述:保存心电图的工具类 创建日期:2016/1/21
 *
 * @author 莫绪旻
 */
public class ECGDataSavaUtil {

    private FileOutputStream mOut = null;
    private BufferedWriter mWriter = null;

    /**
     * 描述：构造器，为一个心电图文件的保存创建Util对象
     *
     * @param ecgRecordFileName
     * @param context
     */
    public ECGDataSavaUtil(String ecgRecordFileName, Context context) {
        try {
            mOut = context.openFileOutput(ecgRecordFileName, Context.MODE_APPEND);
            mWriter = new BufferedWriter(new OutputStreamWriter(mOut));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存单个心电图信号到文件
     *
     * @param ecgData
     */
    public void writeSingleDataToFile(String ecgData) {
        try {
            mWriter.write(ecgData + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
