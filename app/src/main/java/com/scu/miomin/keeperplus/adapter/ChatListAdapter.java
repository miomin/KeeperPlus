package com.scu.miomin.keeperplus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.moke.KeeperDataMoke;
import com.scu.miomin.keeperplus.mvp.model.ChatMessageBean;
import com.scu.miomin.keeperplus.mvp.model.Enum.ChatMsgTypeEnum;
import com.scu.miomin.keeperplus.mvp.model.Userbean;

import java.util.ArrayList;


/**
 * Created by 莫绪旻 on 2015/11/12.
 */
public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ChatMessageBean> chat_msg_list;
    private String userID;
    private Userbean chatFriend;

    public ChatListAdapter(Context context, ArrayList<ChatMessageBean> chat_msg_list, String userID) {

        this.context = context;
        this.chat_msg_list = chat_msg_list;
        this.userID = userID;
        chatFriend = KeeperDataMoke.getInstance().getUserByID(userID);
    }

    @Override
    public int getCount() {
        return chat_msg_list.size();
    }

    @Override
    public Object getItem(int i) {
        return chat_msg_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewholder;

        if (view == null) {

            if (((ChatMessageBean) getItem(i)).getMsgType() == ChatMsgTypeEnum.SEND_MSG) {
                view = View.inflate(context, R.layout.item_chat_list, null);
            } else if (((ChatMessageBean) getItem(i)).getMsgType() == ChatMsgTypeEnum.RECIVE_MSG) {
                view = View.inflate(context, R.layout.item_chat_list, null);
            }
            viewholder = new ViewHolder();
            viewholder.layout_my = (RelativeLayout) view.findViewById(R.id.layout_my);
            viewholder.iv_myhead = (ImageView) view.findViewById(R.id.iv_myhead);
            viewholder.tv_mysendtime = (TextView) view.findViewById(R.id.tv_mysendtime);
            viewholder.tv_mytext = (TextView) view.findViewById(R.id.tv_mytext);
            viewholder.layout_friend = (RelativeLayout) view.findViewById(R.id.layout_friend);
            viewholder.iv_friendhead = (ImageView) view.findViewById(R.id.iv_friendhead);
            viewholder.tv_friendsendtime = (TextView) view.findViewById(R.id.tv_friendsendtime);
            viewholder.tv_friendtext = (TextView) view.findViewById(R.id.tv_friendtext);
            view.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) view.getTag();
        }

        viewholder.iv_myhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewholder.iv_friendhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ChatMessageBean msg = chat_msg_list.get(i);

        switch (msg.getMsgType()) {
            case ChatMsgTypeEnum.RECIVE_MSG:
                viewholder.layout_friend.setVisibility(View.VISIBLE);
                viewholder.layout_my.setVisibility(View.GONE);
                viewholder.tv_friendsendtime.setText(msg.getTime());
                Log.i("keeper", chatFriend.getHeadUrl());
//                MyLoader.dispalyFromAssets(chatFriend.getHeadUrl(), viewholder.iv_friendhead);
                switch (msg.getContentType()) {
                    case ChatMsgTypeEnum.TEXT_MSG:
                        viewholder.tv_friendtext.setText(msg.getText());
                        break;
                    case ChatMsgTypeEnum.PIC_MSG:
                        break;
                    case ChatMsgTypeEnum.VOICE_MSG:
                        break;
                    case ChatMsgTypeEnum.LOCATION_MSG:
                        break;
                    case ChatMsgTypeEnum.ECGRECORD_MSG:
                        break;
                }
                break;
            case ChatMsgTypeEnum.SEND_MSG:
                viewholder.layout_my.setVisibility(View.VISIBLE);
                viewholder.layout_friend.setVisibility(View.GONE);
                viewholder.tv_mysendtime.setText(msg.getTime());
//                MyLoader.dispalyFromAssets(Controller.getCurrentUser().getHeadUrl(), viewholder.iv_myhead);
                switch (msg.getContentType()) {
                    case ChatMsgTypeEnum.TEXT_MSG:
                        viewholder.tv_mytext.setText(msg.getText());
                        break;
                    case ChatMsgTypeEnum.PIC_MSG:
                        break;
                    case ChatMsgTypeEnum.VOICE_MSG:
                        break;
                    case ChatMsgTypeEnum.LOCATION_MSG:
                        break;
                    case ChatMsgTypeEnum.ECGRECORD_MSG:
                        break;
                }
                break;
        }

        return view;
    }

    public void addMsg(ChatMessageBean msg) {
        chat_msg_list.add(msg);
        this.notifyDataSetChanged();
    }

    private class ViewHolder {
        public RelativeLayout layout_my;
        public ImageView iv_myhead;
        public TextView tv_mysendtime;
        public TextView tv_mytext;
        public RelativeLayout layout_friend;
        public ImageView iv_friendhead;
        public TextView tv_friendsendtime;
        public TextView tv_friendtext;
    }
}
