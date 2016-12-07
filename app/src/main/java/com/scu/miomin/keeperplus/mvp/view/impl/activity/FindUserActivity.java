package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * 描述:登录 创建日期:2015/7/23
 *
 * @author miomin
 */
public class FindUserActivity extends ToolbarActivity {

    @Bind(R.id.phone_edit)
    EditText phone_edit;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_find_user, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        setUpTitle("查找医生");

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FindUserActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_find)
    public void findUser() {

        if (TextUtils.isEmpty(phone_edit.getText())) {
            toast("请填写手机号");
            return;
        }

        String phonenumber = phone_edit.getText().toString();

//        showLoading("请稍后", "正在查找");
        BmobQuery<Userbean> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", phonenumber);
        query.include("birthday");
        query.include("hospital");
        query.findObjects(new FindListener<Userbean>() {
            @Override
            public void done(List<Userbean> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        if (list.get(0).getRole() == 2)
                            DoctorInfoActivity2.startActivity(FindUserActivity.this, list.get(0));
                        else
                            toast("对不起，没有查找到任何结果...");
                    } else {
                        toast("对不起，没有查找到任何结果...");
                    }
                } else {
                    toast("对不起，您的网络不太稳定...");
                }
//                hideLoading();
            }
        });
    }
}
