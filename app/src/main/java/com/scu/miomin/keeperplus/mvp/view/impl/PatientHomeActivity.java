package com.scu.miomin.keeperplus.mvp.view.impl;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;


public class PatientHomeActivity extends ToolbarActivity {

    private com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigationView;

    private HomeMsgFragment fragment_msg;
    private HomeMsgFragment fragment_main;
    private HomeMsgFragment fragment_me;


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
    }

    @Override
    protected void setUpView() {
        bottomNavigationView = (com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView)
                findViewById(R.id.bottomNavigation);
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
    }

    private void selectFragmentMsg() {
        if (fragment_msg == null)
            fragment_msg = HomeMsgFragment.newInstance(titles[0]);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment_msg).commitAllowingStateLoss();
        setUpTitle(titles[0]);
    }

    private void selectFragmentMain() {
        if (fragment_main == null)
            fragment_main = HomeMsgFragment.newInstance(titles[1]);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment_main).commitAllowingStateLoss();
        setUpTitle(titles[1]);
    }

    private void selectFragmentMe() {
        if (fragment_me == null)
            fragment_me = HomeMsgFragment.newInstance(titles[2]);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment_me).commitAllowingStateLoss();
        setUpTitle(titles[2]);
    }
}
