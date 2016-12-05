package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;

import butterknife.Bind;

/**
 * 描述:修改系统设置的界面 创建日期:2015/5/10
 *
 * @author 应均康
 */
public class SettingActivity extends ToolbarActivity {

    @Bind(R.id.btn_reciveMessage)
    Switch Btn_reciveMsg;
    @Bind(R.id.btn_vibrate)
    Switch Btn_vibrate;
    @Bind(R.id.btn_voice)
    Switch Btn_voice;

    private final int TOGGLEBTN_ON = 1;
    private final int TOGGLEBTN_OFF = 0;

    private int isReciveMsg = TOGGLEBTN_ON;
    private int isVoice = TOGGLEBTN_ON;
    private int isVibrate = TOGGLEBTN_ON;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_setting, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {

        setUpTitle("系统设置");

        Btn_reciveMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isReciveMsg = TOGGLEBTN_ON;
                } else {
                    isReciveMsg = TOGGLEBTN_OFF;
                }
            }
        });

        Btn_vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isVibrate = TOGGLEBTN_ON;
                } else {
                    isVibrate = TOGGLEBTN_OFF;
                }
            }
        });

        Btn_voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isVoice = TOGGLEBTN_ON;
                } else {
                    isVoice = TOGGLEBTN_OFF;
                }
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        SharedPreferences sp = this.getSharedPreferences("setting",
                MODE_PRIVATE);
        isReciveMsg = sp.getInt("reciveMsg", 1);
        isVoice = sp.getInt("voice", 1);
        isVibrate = sp.getInt("vibrate", 1);
        if (isReciveMsg == TOGGLEBTN_ON) {
            Btn_reciveMsg.setChecked(true);
        } else {
            Btn_reciveMsg.setChecked(false);
        }
        if (isVoice == TOGGLEBTN_ON) {
            Btn_reciveMsg.setChecked(true);
        } else {
            Btn_reciveMsg.setChecked(false);
        }
        if (isVibrate == TOGGLEBTN_ON) {
            Btn_reciveMsg.setChecked(true);
        } else {
            Btn_reciveMsg.setChecked(false);
        }
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sp = this.getSharedPreferences("setting",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putInt("reciveMsg", isReciveMsg);
        editor.putInt("voice", isVoice);
        editor.putInt("vibrate", isVibrate);
        super.onDestroy();
    }
}
