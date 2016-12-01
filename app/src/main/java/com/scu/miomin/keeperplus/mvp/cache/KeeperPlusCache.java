package com.scu.miomin.keeperplus.mvp.cache;

import android.content.Context;

import com.scu.miomin.keeperplus.adapter.ChatListAdapter;
import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.mvp.model.ChatMessageBean;
import com.scu.miomin.keeperplus.mvp.model.DoctorBean;
import com.scu.miomin.keeperplus.mvp.model.Userbean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by miomin on 16/11/21.
 */

public class KeeperPlusCache {

    private static KeeperPlusCache instance;
    private Userbean currentUser;
    private ArrayList<Userbean> friendList = new ArrayList<>();
    private HashMap<String, ChatListAdapter> chatAdapterMap = new HashMap<>();
    public ConversationAdapter conversationAdapter;
    private ArrayList<DoctorBean> remendoctorArray = new ArrayList<>();

    private KeeperPlusCache() {

    }

    public static KeeperPlusCache getInstance() {
        if (instance == null) {
            synchronized (KeeperPlusCache.class) {
                if (instance == null)
                    instance = new KeeperPlusCache();
            }
        }
        return instance;
    }

    public void setCurrentUser(Userbean userbean) {
        this.currentUser = userbean;
    }

    public Userbean getCurrentUser() {
        return currentUser;
    }

    // 根据ID获取好友
    public Userbean getFriendByID(String phonenumber) {
        Userbean userbean = null;
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).getAccount().equals(phonenumber)) {
                userbean = friendList.get(i);
            }
        }
        return userbean;
    }

    public void addFriend(Userbean userbean) {
        friendList.add(userbean);
    }

    public void initChatAdapterList(Context context) {
        for (int i = 0; i < friendList.size(); i++) {
            chatAdapterMap.put(friendList.get(i).getAccount(),
                    new ChatListAdapter(context, new ArrayList<ChatMessageBean>(),
                            friendList.get(i).getAccount()));
        }
    }

    public ChatListAdapter getChatAdapter(String userphone) {
        return chatAdapterMap.get(userphone);
    }

    public ConversationAdapter getConversationAdapter() {
        return conversationAdapter;
    }

    public void setConversationAdapter(ConversationAdapter conversationAdapter) {
        this.conversationAdapter = conversationAdapter;
    }

    public void addRemenDoctor(DoctorBean doctorBean) {
        remendoctorArray.add(doctorBean);
    }

    public ArrayList<DoctorBean> getRemenDoctorArray() {
        return remendoctorArray;
    }
}
