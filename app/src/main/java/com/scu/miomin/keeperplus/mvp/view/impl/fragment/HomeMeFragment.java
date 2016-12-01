package com.scu.miomin.keeperplus.mvp.view.impl.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.core.BaseFragment;

/**
 * Created by 莫绪旻 on 16/11/29.
 */
public class HomeMeFragment extends BaseFragment {


    private ListView lvConversation;
    private final static String TITLE = "title";

    // fragment的布局
    private ProgressDialog dialog;

    public static HomeMeFragment newInstance(String title) {
        HomeMeFragment fragment = new HomeMeFragment();
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