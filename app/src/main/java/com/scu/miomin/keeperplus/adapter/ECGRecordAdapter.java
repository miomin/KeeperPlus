package com.scu.miomin.keeperplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.mvp.model.ECGRecordBean;

import java.util.ArrayList;


/**
 * 描述:对话列表适配器 创建日期:2015/11/10
 *
 * @author 莫绪旻
 */
public class ECGRecordAdapter extends BaseAdapter {

    private ArrayList<ECGRecordBean> listECGRecord;
    private Context context;

    // 构造器
    public ECGRecordAdapter(Context context) {
        super();
        this.listECGRecord = new ArrayList<>();
        this.context = context;
        notifyDataSetChanged();
    }

    public void pushSucceed(int position) {
        listECGRecord.get(position - 1).setisLocality(true);
        notifyDataSetChanged();
    }

    // 添加一条记录
    public void add(ECGRecordBean ecgRecord) {
        listECGRecord.add(ecgRecord);
        notifyDataSetChanged();
    }

    // 删除一条记录
    public void delete(int index) {
        listECGRecord.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listECGRecord.size();
    }

    @Override
    public Object getItem(int position) {
        return listECGRecord.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 保留一个item的控件
        viewHolder holder = null;

        if (convertView == null) {
            // 拿到ListViewItem的布局（一行，需要单独定义一个），转换为View类型的对象
            convertView = View.inflate(context, R.layout.item_ecg_record, null);
            holder = new viewHolder();
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.ivState = (ImageView) convertView.findViewById(R.id.ivState);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        // 更新保留的控件中的数据
        ECGRecordBean ecgRecord = listECGRecord.get(position);

        holder.tvDate.setText(ecgRecord.getDate());
        return convertView;
    }

    class viewHolder {
        TextView tvDate;
        ImageView ivState;
    }
}