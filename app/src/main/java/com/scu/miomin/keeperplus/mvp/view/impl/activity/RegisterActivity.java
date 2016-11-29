package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.mvp.presenter.impl.RegisterPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IRegisterView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 描述:注册界面 创建日期:2015/7/23
 *
 * @author 莫绪旻
 */
public class RegisterActivity extends BaseToolbarMvpActivity<RegisterPresenter> implements IRegisterView {

    @Bind(R.id.id_edit)
    EditText id_edit;
    @Bind(R.id.id_cancel)
    LinearLayout id_cancel;
    @Bind(R.id.pw_edit)
    EditText pw_edit;
    @Bind(R.id.pw_cancel)
    LinearLayout pw_cancel;
    @Bind(R.id.code_edit)
    EditText code_edit;
    @Bind(R.id.btn_getCode)
    Button btn_getCode;
    @Bind(R.id.btn_register)
    Button btn_register;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void setUpView() {
        setUpTitle(getResources().getString(R.string.register));
        initView();
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    private void initView() {

        id_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(id_edit.getText())) {
                    id_cancel.setVisibility(View.VISIBLE);
                    btn_getCode.setEnabled(true);
                    if (!TextUtils.isEmpty(pw_edit.getText()) && !TextUtils.isEmpty(code_edit.getText())) {
                        btn_register.setEnabled(true);
                    } else {
                        btn_register.setEnabled(false);
                    }
                } else {
                    id_cancel.setVisibility(View.INVISIBLE);
                    btn_getCode.setEnabled(false);
                    btn_register.setEnabled(false);
                }
            }
        });

        pw_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(pw_edit.getText())) {
                    pw_cancel.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(id_edit.getText()) && !TextUtils.isEmpty(code_edit.getText())) {
                        btn_register.setEnabled(true);
                    } else {
                        btn_register.setEnabled(false);
                    }
                } else {
                    pw_cancel.setVisibility(View.INVISIBLE);
                    btn_register.setEnabled(false);
                }
            }
        });

        code_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(code_edit.getText())) {
                    if (!TextUtils.isEmpty(id_edit.getText()) && !TextUtils.isEmpty(pw_edit.getText())) {
                        btn_register.setEnabled(true);
                    } else {
                        btn_register.setEnabled(false);
                    }
                } else {
                    btn_register.setEnabled(false);
                }
            }
        });

        RxView.clicks(btn_getCode)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        mvpPresenter.getTelCode();
                    }
                });

        RxView.clicks(btn_register)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        mvpPresenter.register();
                    }
                });
    }

    @OnClick(R.id.id_cancel)
    public void clearID() {
        id_edit.setText("");
    }

    @OnClick(R.id.pw_cancel)
    public void clearPW() {
        pw_edit.setText("");
    }

    @Override
    public String getUserName() {
        return id_edit.getText().toString();
    }

    @Override
    public String getPassword() {
        return pw_edit.getText().toString();
    }

    @Override
    public String getCode() {
        return code_edit.getText().toString();
    }

    @Override
    public void setCodeBtnEnable(boolean enable) {
        btn_getCode.setEnabled(enable);
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }
}
