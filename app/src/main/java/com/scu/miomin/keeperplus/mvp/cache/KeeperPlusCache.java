package com.scu.miomin.keeperplus.mvp.cache;

import android.content.Context;

import com.scu.miomin.keeperplus.adapter.ChatListAdapter;
import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.adapter.RemenDoctorAdapter;
import com.scu.miomin.keeperplus.mvp.model.ChatMessageBean;
import com.scu.miomin.keeperplus.mvp.model.Userbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by miomin on 16/11/21.
 */

public class KeeperPlusCache {

    private static KeeperPlusCache instance;
    private Userbean currentUser;
    private ArrayList<Userbean> friendList = new ArrayList<>();
    private HashMap<String, ChatListAdapter> chatAdapterMap = new HashMap<>();
    private ConversationAdapter conversationAdapter;
    private RemenDoctorAdapter remenDoctorAdapter;
    private ArrayList<Userbean> remenDoctorList = new ArrayList<>();

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
            if (friendList.get(i).getMobilePhoneNumber().equals(phonenumber)) {
                userbean = friendList.get(i);
            }
        }
        return userbean;
    }

    public int getFriendCount() {
        if (friendList == null)
            return 0;
        return friendList.size();
    }

    public void addFriend(Userbean userbean) {
        friendList.add(userbean);
    }

    public void clearFriend() {
        friendList.clear();
    }

    public void initChatAdapterList(Context context) {
        for (int i = 0; i < friendList.size(); i++) {
            chatAdapterMap.put(friendList.get(i).getMobilePhoneNumber(),
                    new ChatListAdapter(context, new ArrayList<ChatMessageBean>(),
                            friendList.get(i).getMobilePhoneNumber()));
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

    public RemenDoctorAdapter getRemenDoctorAdapter() {
        return remenDoctorAdapter;
    }

    public void setRemenDoctorAdapter(RemenDoctorAdapter remenDoctorAdapter) {
        this.remenDoctorAdapter = remenDoctorAdapter;
    }

    public void addRemenDoctor(Userbean doctorBean) {
        remenDoctorAdapter.add(doctorBean);
        remenDoctorList.add(doctorBean);
    }

    public void addRemenDoctor(List<Userbean> doctorList) {
        remenDoctorAdapter.add(doctorList);
        remenDoctorList.addAll(doctorList);
    }

    public ArrayList<Userbean> getRemenDoctorList() {
        return remenDoctorList;
    }

    public int getRemenDoctorCount() {
        if (remenDoctorList == null)
            return 0;
        return remenDoctorList.size();
    }

    public void refreshRemenDoctorAdapter() {
        remenDoctorAdapter.add(remenDoctorList);
    }
}
