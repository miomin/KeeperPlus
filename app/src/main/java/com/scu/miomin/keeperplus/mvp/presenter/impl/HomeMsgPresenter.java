package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.ChatMessageBean;
import com.scu.miomin.keeperplus.mvp.model.ConversationBean;
import com.scu.miomin.keeperplus.mvp.model.Enum.ChatMsgTypeEnum;
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
    // 消息接受的观察者
    Observer<List<IMMessage>> incomingMessageObserver;

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
        incomingMessageObserver =
                new Observer<List<IMMessage>>() {
                    @Override
                    public void onEvent(List<IMMessage> messages) {
                        // 处理新收到的消息，为了上传处理方便，SDK 保证参数 messages 全部来自同一个聊天对象。
                        for (int i = 0; i < messages.size(); i++) {
                            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                            Date currentData = new Date(System.currentTimeMillis());
                            String time = format.format(currentData);
                            ChatMessageBean textMsg = new ChatMessageBean(messages.get(i).getSessionId(),
                                    KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber(),
                                    messages.get(i).getContent(), time, ChatMsgTypeEnum.RECIVE_MSG);
                            // 显示最后一行
//                            if (ChatActivity.instance != null)
//                                ChatActivity.addMsg(messages.get(i).getSessionId(), textMsg);
//                            else
                            KeeperPlusCache.getInstance().getChatAdapter(messages.get(i).getSessionId()).addMsg(textMsg);
                        }
                    }
                };

        // 注册观察者对象
        NIMClient.getService(MsgServiceObserve.class)
                .observeReceiveMessage(incomingMessageObserver, true);
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

    @Override
    public void initChatAdapterMap() {
        KeeperPlusCache.getInstance().initChatAdapterList(mvpView.getViewActivity());
    }
}
