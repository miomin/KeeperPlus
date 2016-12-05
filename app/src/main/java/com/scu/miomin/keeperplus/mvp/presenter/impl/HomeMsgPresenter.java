package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.ConversationBean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.IHomeMsgPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeMsgView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by miomin on 16/11/16.
 */

public class HomeMsgPresenter extends BasePresenter<IHomeMsgView> implements IHomeMsgPresenter {

    //  收发消息更新对话列表的观察者对象
    Observer<List<RecentContact>> conversationObserver;

    public HomeMsgPresenter(IHomeMsgView iHomeMsgView) {
        attachView(iHomeMsgView);
    }

    @Override
    public void initConversationObserver() {
        conversationObserver =
                new Observer<List<RecentContact>>() {
                    @Override
                    public void onEvent(List<RecentContact> messages) {
                        // recents参数即为最近联系人列表（最近会话列表）
                        for (int i = 0; i < messages.size(); i++) {
                            String lastMsg = messages.get(i).getContent();
                            long time = messages.get(i).getTime();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");
                            String lastTime = sdf.format(new Date(time));
                            boolean isNewMsg = true;
                            String userphone = messages.get(i).getContactId();
                            String username = KeeperPlusCache.getInstance().getFriendByID(userphone).getUsername();

                            ConversationBean conversation = new ConversationBean(lastMsg, lastTime,
                                    isNewMsg, userphone, username);
                            KeeperPlusCache.getInstance().getConversationAdapter().add(conversation);
                        }
                    }
                };

        //  注册观察者
        NIMClient.getService(MsgServiceObserve.class)
                .observeRecentContact(conversationObserver, true);
    }

    @Override
    public void initMsgReciverObserver() {

    }

    @Override
    public void initConversationAdapter() {

        // 创建适配器对象
        KeeperPlusCache.getInstance().setConversationAdapter(new ConversationAdapter(mvpView.getViewActivity()));
        mvpView.setConversationAdapter(KeeperPlusCache.getInstance().getConversationAdapter());

        NIMClient.getService(MsgService.class).queryRecentContacts()
                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable exception) {
                        // recents参数即为最近联系人列表（最近会话列表）
                        for (int i = 0; i < recents.size(); i++) {
                            String lastMsg = recents.get(i).getContent();
                            long time = recents.get(i).getTime();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");
                            String lastTime = sdf.format(new Date(time));
                            boolean isNewMsg = true;

                            String userphone = recents.get(i).getContactId();

                            String username = KeeperPlusCache.getInstance().getFriendByID(userphone).getUsername();

                            ConversationBean conversation = new ConversationBean(lastMsg, lastTime,
                                    isNewMsg, userphone, username);
                            KeeperPlusCache.getInstance().getConversationAdapter().add(conversation);
                        }
                    }
                });
    }
}
