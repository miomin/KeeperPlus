package com.scu.miomin.keeperplus.mvp.model;


import java.io.Serializable;

/**
 * 描述:对话项实体类 创建日期:2015/11/10
 *
 * @author 莫绪旻
 */
public class ConversationBean implements Serializable {

    private String phonenumber;
    private String username;
    private String lastMsg;
    private String lastTime;
    private boolean newMsg;

    public ConversationBean(String lastMsg, String lastTime, boolean newMsg, String phonenumber, String username) {
        this.lastMsg = lastMsg;
        this.lastTime = lastTime;
        this.newMsg = newMsg;
        this.phonenumber = phonenumber;
        this.username = username;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public boolean isNewMsg() {
        return newMsg;
    }

    public void setNewMsg(boolean newMsg) {
        this.newMsg = newMsg;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ConversationBean{" +
                "lastMsg='" + lastMsg + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", username='" + username + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", newMsg=" + newMsg +
                '}';
    }
}
