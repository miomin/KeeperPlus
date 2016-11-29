package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.presenter.impl.LoginPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ILoginView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;


/**
 * 描述:登录 创建日期:2015/7/23
 *
 * @author miomin
 */
public class LoginActivity extends BaseToolbarMvpActivity<LoginPresenter> implements ILoginView {

    @Bind(R.id.id_edit)
    EditText id_edit;
    @Bind(R.id.pw_edit)
    EditText pw_edit;
    @Bind(R.id.id_cancel)
    LinearLayout id_cancel;
    @Bind(R.id.pw_cancel)
    LinearLayout pw_cancel;
    @Bind(R.id.btn_login)
    Button btn_login;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_login, ActivityType.MODE_TOOLBAR);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void setUpView() {

        setUpTitle(getResources().getString(R.string.login));

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
                    if (!TextUtils.isEmpty(pw_edit.getText())) {
                        btn_login.setEnabled(true);
                    } else {
                        btn_login.setEnabled(false);
                    }
                } else {
                    id_cancel.setVisibility(View.INVISIBLE);
                    btn_login.setEnabled(false);
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
                    if (!TextUtils.isEmpty(id_edit.getText())) {
                        btn_login.setEnabled(true);
                    } else {
                        btn_login.setEnabled(false);
                    }
                } else {
                    pw_cancel.setVisibility(View.INVISIBLE);
                    btn_login.setEnabled(false);
                }
            }
        });

        RxView.clicks(btn_login)
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
                        mvpPresenter.login();
                    }
                });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.id_cancel)
    public void clearAccount() {
        id_edit.setText("");
        id_cancel.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.pw_cancel)
    public void clearPassword() {
        pw_edit.setText("");
        pw_cancel.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mvpPresenter.scanLastLogininfo();
    }

    @Override
    public String getUserName() {
        return id_edit.getText().toString().toLowerCase();
    }

    @Override
    public String getPassword() {
        return pw_edit.getText().toString().toLowerCase();
    }

    @Override
    public void setUserNameText(String userName) {
        id_edit.setText(userName);
    }

    @Override
    public SharedPreferences getSharedPreferences(String spName) {
        return getSharedPreferences(spName, MODE_PRIVATE);
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, PatientHomeActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_register) {
            RegisterActivity.startActivity(LoginActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
