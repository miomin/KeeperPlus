package com.scu.miomin.keeperplus.mvp.view.impl;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.core.BaseFragment;

/**
 * Created by 莫绪旻 on 16/2/29.
 */
public class HomeMsgFragment extends BaseFragment {

    private final static String TITLE = "title";

    // fragment的布局
    private ProgressDialog dialog;

    public static HomeMsgFragment newInstance(String title) {
        HomeMsgFragment fragment = new HomeMsgFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments().getString(TITLE);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_msg, container, false);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getResources().getString(R.string.app_name));
    }
}
