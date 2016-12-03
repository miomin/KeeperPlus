package com.scu.miomin.keeperplus.mvp.view.impl.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.adapter.RemenDoctorAdapter;
import com.scu.miomin.keeperplus.mvp.presenter.impl.HomeMainPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeMainView;
import com.scu.miomin.keeperplus.mvpcore.BaseMvpFragment;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 莫绪旻 on 16/11/29.
 */
public class HomeMainFragment extends BaseMvpFragment<HomeMainPresenter> implements IHomeMainView {

    private final static String TITLE = "title";

    private ListView lvRemenDoctor;

    public static HomeMainFragment newInstance(String title) {
        HomeMainFragment fragment = new HomeMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected HomeMainPresenter createPresenter() {
        return new HomeMainPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments().getString(TITLE);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_main, container, false);
    }

    @Override
    protected void setUpView() {
        lvRemenDoctor = (ListView) fragmentView.findViewById(R.id.lvRemenDoctor);

        BmobQuery<BmobUser> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", "18084803926");
        query.findObjects(new FindListener<BmobUser>() {
            @Override
            public void done(List<BmobUser> list, BmobException e) {
                if (e == null) {
                    for (BmobUser bmobUser : list) {
                        Toast.makeText(getActivity(), bmobUser.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void setUpData() {
        // 初始化热门医生列表的适配器
        mvpPresenter.initRemenDoctorAdapter();
        // 初始化热门医生列表的监听器
        mvpPresenter.initRemenDoctorListener();
    }

    @Override
    public void setRemenDoctorAdapter(RemenDoctorAdapter remenDoctorAdapter) {
        lvRemenDoctor.setAdapter(remenDoctorAdapter);
    }

    @Override
    public Activity getViewActivity() {
        return getActivity();
    }
}
