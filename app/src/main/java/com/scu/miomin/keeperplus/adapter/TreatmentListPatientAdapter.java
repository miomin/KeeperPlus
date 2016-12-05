package com.scu.miomin.keeperplus.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;

import java.util.ArrayList;


/**
 * 描述:病人端诊后随访列表的适配器 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentListPatientAdapter extends BaseAdapter {

    private ArrayList<TreatmentBean> listTreatmentFollowerForPatient;
    private Context context;

    // 构造器
    public TreatmentListPatientAdapter(Context context) {
        super();
        this.listTreatmentFollowerForPatient = new ArrayList<TreatmentBean>();
        this.context = context;
        notifyDataSetChanged();
    }

    // 构造器
    public TreatmentListPatientAdapter(Context context, ArrayList<TreatmentBean> listTreatmentFollowerForPatient) {
        super();
        this.listTreatmentFollowerForPatient = listTreatmentFollowerForPatient;
        this.context = context;
        notifyDataSetChanged();
    }

    public void add(TreatmentBean treatmentFollowupForPatientBean) {
        listTreatmentFollowerForPatient.add(treatmentFollowupForPatientBean);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listTreatmentFollowerForPatient.size();
    }

    @Override
    public Object getItem(int position) {
        return listTreatmentFollowerForPatient.get(position);
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
            convertView = View.inflate(context, R.layout.item_treatment_patient_list, null);
            holder = new viewHolder();
            holder.ivHead = (SimpleDraweeView) convertView.findViewById(R.id.ivHead);
            holder.tvHospital = (TextView) convertView.findViewById(R.id.tvHospital);
            holder.tvName = (TextView) convertView
                    .findViewById(R.id.tvName);
            holder.tvTreatmentReason = (TextView) convertView
                    .findViewById(R.id.tvTreatmentReason);
            holder.tvTreatmentDate = (TextView) convertView
                    .findViewById(R.id.tvTreatmentDate);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        // 更新保留的控件中的数据
        TreatmentBean treatmentBean =
                listTreatmentFollowerForPatient.get(position);

        if (treatmentBean == null)
            return null;

        holder.tvHospital.setText(treatmentBean.getDoctorBean().getHospital().getName());
        holder.tvName.setText(treatmentBean.getDoctorBean().getUsername());
        holder.tvTreatmentReason.setText(treatmentBean.getTreatmentReason());
        holder.tvTreatmentDate.setText(treatmentBean.getDate());

        Uri uri = Uri.parse(treatmentBean.getDoctorBean().getHeadUrl());
        holder.ivHead.setImageURI(uri);

        // 将更新后的控件返回给Android系统
        return convertView;
    }

    class viewHolder {
        SimpleDraweeView ivHead;
        TextView tvHospital;
        TextView tvName;
        TextView tvTreatmentReason;
        TextView tvTreatmentDate;
    }
}