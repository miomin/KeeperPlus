package com.scu.miomin.keeperplus.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.scu.miomin.keeperplus.mvp.view.impl.activity.LoginActivity;
import com.scu.miomin.keeperplus.mvp.view.impl.activity.PatientHomeActivity;

/**
 * Created by 莫绪旻 on 16/7/15.
 */
public class UIHelper {

    public final static String TAG = "UIHelper";

    public final static int RESULT_OK = 0x00;
    public final static int REQUEST_CODE = 0x01;

    public static void ToastMessage(Context cont, String msg) {
        if (cont == null || msg == null) {
            return;
        }
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, int msg) {
        if (cont == null || msg <= 0) {
            return;
        }
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, String msg, int time) {
        if (cont == null || msg == null) {
            return;
        }
        Toast.makeText(cont, msg, time).show();
    }

    public static void showLogin(Activity context) {
        LoginActivity.startActivity(context);
    }

    public static void showPatientHome(Activity context) {
        PatientHomeActivity.startActivity(context);
    }
}
