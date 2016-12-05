package com.scu.miomin.keeperplus.mvp.view.impl.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.core.BaseFragment;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;

/**
 * Created by 莫绪旻 on 16/11/29.
 */
public class HomeMeFragment extends BaseFragment {

    private final static String TITLE = "title";

    private TextView tvPhonenumber_top;
    private TextView tvPhonenumber;
    private TextView tvPatientname;
    private SimpleDraweeView ivHead;

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
        return inflater.inflate(R.layout.fragment_home_me, container, false);
    }

    @Override
    protected void setUpView() {

        tvPatientname = (TextView) fragmentView.findViewById(R.id.tvUsername);
        tvPhonenumber_top = (TextView) fragmentView.findViewById(R.id.tvUserphone_top);
        tvPhonenumber = (TextView) fragmentView.findViewById(R.id.tvUserphone);
        ivHead = (SimpleDraweeView) fragmentView.findViewById(R.id.ivHead);

        String phonenumber = KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber().substring(0, 3)
                + "****"
                + KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber().substring(7, 11);

        tvPatientname.setText(KeeperPlusCache.getInstance().getCurrentUser().getUsername());
        tvPhonenumber_top.setText(phonenumber);
        tvPhonenumber.setText(phonenumber);
    }

    @Override
    protected void setUpData() {
        Uri uri = Uri.parse(KeeperPlusCache.getInstance().getCurrentUser().getHeadUrl());
        ivHead.setImageURI(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof ToolbarActivity) {
            ((ToolbarActivity) getActivity()).hideToolbar();
        } else if (getActivity() instanceof BaseToolbarMvpActivity) {
            ((BaseToolbarMvpActivity) getActivity()).hideToolbar();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() instanceof ToolbarActivity || getActivity() instanceof BaseToolbarMvpActivity) {
            ((ToolbarActivity) getActivity()).showToolbar();
        } else if (getActivity() instanceof BaseToolbarMvpActivity) {
            ((BaseToolbarMvpActivity) getActivity()).hideToolbar();
        }
    }
}
