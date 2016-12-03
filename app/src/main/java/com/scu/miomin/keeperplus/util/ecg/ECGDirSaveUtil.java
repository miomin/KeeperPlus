package com.scu.miomin.keeperplus.util.ecg;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * 描述:心电图目录Util 创建日期:2016/1/21
 *
 * @author 莫绪旻
 */
public class ECGDirSaveUtil {

    public static String RECORE_DIR_FILE_NAME = "ecg_record_dir.txt";

    public static void creatDirFile(Context context) {
        FileOutputStream out = null;
        try {
            out = context.openFileOutput(RECORE_DIR_FILE_NAME, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                out = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeRecordToDir(String ecgRecordFilename, Context context) {
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = context.openFileOutput(RECORE_DIR_FILE_NAME, Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(ecgRecordFilename + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                writer.flush();
                out.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> readRecordDir(Context context) {

        FileInputStream in = null;
        BufferedReader reader = null;
        ArrayList<String> dirArrayList = new ArrayList<String>();

        try {
            in = context.openFileInput(RECORE_DIR_FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = reader.readLine()) != null) {
                dirArrayList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                reader.close();
                in = null;
                reader = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dirArrayList;
    }
}
