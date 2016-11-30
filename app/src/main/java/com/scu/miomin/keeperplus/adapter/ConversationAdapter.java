package com.scu.miomin.keeperplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.mvp.model.ConversationBean;

import java.util.ArrayList;


/**
 * 描述:对话列表适配器 创建日期:2015/11/10
 *
 * @author 莫绪旻
 */
public class ConversationAdapter extends BaseAdapter {

    private ArrayList<ConversationBean> listConversation;
    private Context context;

    // 构造器
    public ConversationAdapter(Context context) {
        super();
        this.listConversation = new ArrayList<ConversationBean>();
        this.context = context;
        notifyDataSetChanged();
    }

    // 添加一条会话信息
    public void add(ConversationBean conversation) {

        int index = isExit(conversation.getPhonenumber());
        if (index == -1)
            listConversation.add(0, conversation);
        else {
            listConversation.remove(index);
            listConversation.add(0, conversation);
        }

        notifyDataSetChanged();
    }

    public ConversationBean getConversation(int position) {
        return listConversation.get(position);
    }

    @Override
    public int getCount() {
        return listConversation.size();
    }

    @Override
    public Object getItem(int position) {
        return listConversation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 保留一个item的控件
        viewHolder holder = null;

        if (convertView == null) {
            // 拿到ListViewItem的布局（一行，需要单独定义一个），转换为View类型的对象
            convertView = View.inflate(context, R.layout.item_conversation, null);
            holder = new viewHolder();
            holder.ivHead = (ImageView) convertView
                    .findViewById(R.id.ivHead);
            holder.tvName = (TextView) convertView
                    .findViewById(R.id.tvName);
            holder.tvMsg = (TextView) convertView
                    .findViewById(R.id.tvMsg);
            holder.tvDate = (TextView) convertView
                    .findViewById(R.id.tvDate);
            holder.point = (ImageView) convertView
                    .findViewById(R.id.point);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        // 更新保留的控件中的数据
        ConversationBean conversation = listConversation.get(position);

//        String imageUrl = MyUrl.HEADURLPATH + conversation.getPhonenumber() + ".jpg";

//        holder.ivHead.setTag(imageUrl);

//        if (imageUrl.equals(holder.ivHead.getTag())) {
//            MyLoader.dispalyFromAssets(UserResource.getUserByID(conversation.getPhonenumber()).getHeadUrl(),
//                    holder.ivHead);
//        }

        holder.tvName.setText(conversation.getUsername());
        holder.tvMsg.setText(conversation.getLastMsg());
        holder.tvDate.setText(conversation.getLastTime());

        if (conversation.isNewMsg())
            holder.point.setVisibility(View.VISIBLE);
        else
            holder.point.setVisibility(View.INVISIBLE);

        // 将更新后的控件返回给Android系统
        return convertView;
    }

    class viewHolder {
        ImageView ivHead;
        TextView tvName;
        TextView tvMsg;
        TextView tvDate;
        ImageView point;
    }

    // 判断当前要添加的会话对应的用户是否已经在列表里
    private int isExit(String phonenumber) {

        for (int i = 0; i < listConversation.size(); i++) {
            if (listConversation.get(i).getPhonenumber().equals(phonenumber)) {
                return i;
            }
        }
        return -1;
    }
}