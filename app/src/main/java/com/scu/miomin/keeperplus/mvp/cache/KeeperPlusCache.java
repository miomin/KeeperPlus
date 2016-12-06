package com.scu.miomin.keeperplus.mvp.cache;

import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.adapter.RemenDoctorAdapter;
import com.scu.miomin.keeperplus.adapter.TreatmentFollowupAdapter;
import com.scu.miomin.keeperplus.adapter.TreatmentListPatientAdapter;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.model.Userbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miomin on 16/11/21.
 */

public class KeeperPlusCache {

    private static KeeperPlusCache instance;
    private Userbean currentUser;
    private ArrayList<Userbean> friendList = new ArrayList<>();
    private ArrayList<Userbean> remenDoctorList = new ArrayList<>();
    private ArrayList<TreatmentBean> treatmentArray = new ArrayList<>();

    private ConversationAdapter conversationAdapter;
    private RemenDoctorAdapter remenDoctorAdapter;
    private TreatmentListPatientAdapter treatmentListAdapter;
    private TreatmentFollowupAdapter treatmentFollowupAdapter;
    private String lastCheckUserPhonenumber;

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

    public TreatmentListPatientAdapter getTreatmentListAdapter() {
        return treatmentListAdapter;
    }

    public void setTreatmentListAdapter(TreatmentListPatientAdapter treatmentListAdapter) {
        this.treatmentListAdapter = treatmentListAdapter;
    }

    public ArrayList<TreatmentBean> getTreatmentArray() {
        return treatmentArray;
    }

    public void addTreatment(TreatmentBean treatmentBean) {
        treatmentArray.add(treatmentBean);
    }

    public void clearTreatmentData() {
        treatmentArray.clear();
    }

    public TreatmentFollowupAdapter getTreatmentFollowupAdapter() {
        return treatmentFollowupAdapter;
    }

    public void setTreatmentFollowupAdapter(TreatmentFollowupAdapter treatmentFollowupAdapter) {
        this.treatmentFollowupAdapter = treatmentFollowupAdapter;
    }

    public String getLastCheckUserPhonenumber() {
        return lastCheckUserPhonenumber;
    }

    public void setLastCheckUserPhonenumber(String lastCheckUserPhonenumber) {
        this.lastCheckUserPhonenumber = lastCheckUserPhonenumber;
    }
}
