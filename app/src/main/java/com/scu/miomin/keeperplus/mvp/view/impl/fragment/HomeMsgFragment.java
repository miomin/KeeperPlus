package com.scu.miomin.keeperplus.mvp.view.impl.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.mvp.presenter.impl.HomeMsgPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeMsgView;
import com.scu.miomin.keeperplus.mvpcore.BaseMvpFragment;

/**
 * Created by 莫绪旻 on 16/2/29.
 */
public class HomeMsgFragment extends BaseMvpFragment<HomeMsgPresenter> implements IHomeMsgView {

    private final static String TITLE = "title";

    private ListView lvConversation;

    public static HomeMsgFragment newInstance(String title) {
        HomeMsgFragment fragment = new HomeMsgFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected HomeMsgPresenter createPresenter() {
        return new HomeMsgPresenter(this);
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
        lvConversation = (ListView) fragmentView.findViewById(R.id.lvConversation);
    }

    @Override
    protected void setUpData() {
        // 初始化对话列表的适配器
        mvpPresenter.initConversationAdapter();
        //  创建收发消息更新对话列表的观察者对象
        mvpPresenter.initConversationObserver();
        // 初始化chat适配器
        mvpPresenter.initChatAdapterMap();
        // 初始化接受消息的观察者对象
        mvpPresenter.initMsgReciverObserver();
    }

    @Override
    public void setConversationAdapter(ConversationAdapter conversationAdapter) {
        // 将ListView与适配器关联
        lvConversation.setAdapter(conversationAdapter);
    }

    @Override
    public Activity getViewActivity() {
        return getActivity();
    }
}
