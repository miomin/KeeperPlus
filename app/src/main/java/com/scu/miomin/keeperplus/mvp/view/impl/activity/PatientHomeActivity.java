package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.core.BaseFragment;
import com.scu.miomin.keeperplus.mvp.presenter.impl.HomePresenter;
import com.scu.miomin.keeperplus.mvp.view.impl.fragment.HomeMainFragment;
import com.scu.miomin.keeperplus.mvp.view.impl.fragment.HomeMeFragment;
import com.scu.miomin.keeperplus.mvp.view.impl.fragment.HomeMsgFragment;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeView;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;

import me.drakeet.materialdialog.MaterialDialog;


public class PatientHomeActivity extends BaseToolbarMvpActivity<HomePresenter> implements IHomeView {

    private com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigationView;

    private BaseFragment fragment_msg;
    private BaseFragment fragment_main;
    private BaseFragment fragment_me;

    private int[] image = {R.drawable.icon_msg_tab, R.drawable.icon_main_tab,
            R.drawable.icon_myself_tab};

    private String[] titles = new String[3];

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_patient_home, ActivityType.MODE_TOOLBAR);
        setUpTitle(getResources().getString(R.string.app_name));
        titles[0] = getResources().getString(R.string.msg);
        titles[1] = getResources().getString(R.string.main);
        titles[2] = getResources().getString(R.string.me);
        // 初始化好友列表
        mvpPresenter.initFriendList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter.clearFriendList();
    }

    @Override
    protected void setUpView() {
        bottomNavigationView = (com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView)
                findViewById(R.id.bottomNavigation);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PatientHomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(true);
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(40);
            bottomNavigationView.setTextInactiveSize(36);
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.colorPrimary));
//            bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Noh_normal.ttf"));
        }

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                (titles[0], R.color.colorPrimary, image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                (titles[1], R.color.colorPrimary, image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                (titles[2], R.color.colorPrimary, image[2]);


        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                        selectFragmentMsg();
                        break;
                    case 1:
                        selectFragmentMain();
                        break;
                    case 2:
                        selectFragmentMe();
                        break;
                }
            }
        });

        selectFragmentMain();
        bottomNavigationView.selectTab(1);
    }

    private void selectFragmentMsg() {
        if (fragment_msg == null)
            fragment_msg = HomeMsgFragment.newInstance(titles[0]);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment_msg).commitAllowingStateLoss();
        setUpTitle(titles[0]);
    }

    private void selectFragmentMain() {
        if (fragment_main == null)
            fragment_main = HomeMainFragment.newInstance(titles[1]);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment_main).commitAllowingStateLoss();
        setUpTitle(titles[1]);
    }

    private void selectFragmentMe() {
        if (fragment_me == null)
            fragment_me = HomeMeFragment.newInstance(titles[2]);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment_me).commitAllowingStateLoss();
        setUpTitle(titles[2]);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            SettingActivity.startActivity(PatientHomeActivity.this);
            return true;
        } else if (id == R.id.action_quit) {
            final MaterialDialog mMaterialDialog = new MaterialDialog(PatientHomeActivity.this);
            mMaterialDialog.setTitle("暂时不要退出哟")
                    .setMessage("再给其它两位专家看看，您先别退出哟~")
                    .setPositiveButton("好的", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });

            mMaterialDialog.show();
            return true;
        } else if (id == R.id.action_find_friend) {
            FindUserActivity.startActivity(PatientHomeActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 监听到返回键时，退出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final MaterialDialog mMaterialDialog = new MaterialDialog(PatientHomeActivity.this);
            mMaterialDialog.setTitle("暂时不要退出哟")
                    .setMessage("再给其它两位专家看看，您先别退出哟~")
                    .setPositiveButton("好的", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });

            mMaterialDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
