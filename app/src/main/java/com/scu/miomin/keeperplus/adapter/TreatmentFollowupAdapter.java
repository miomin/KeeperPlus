package com.scu.miomin.keeperplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.model.TreatmentFollowupBean;

import java.util.ArrayList;


/**
 * Created by miomin on 15/11/22.
 */
public class TreatmentFollowupAdapter extends BaseAdapter {

    private TextView tvDoctorName;
    private TextView tvAdministrative;
    private TextView tvProfessional;
    private TextView tvTreatmentDate;
    private TextView tvTreatmentHospital;
    private TextView tvPatientName;
    private TextView tvTreatmentReason;
    private ImageView ivDoctorHead;

    private ArrayList<TreatmentFollowupBean> treatmentFollowupList;
    private Context context;
    private TreatmentBean treatmentBean;

    public TreatmentFollowupAdapter(ArrayList<TreatmentFollowupBean> treatmentFollowupList,
                                    Context context, TreatmentBean treatmentBean) {
        this.context = context;
        this.treatmentFollowupList = treatmentFollowupList;
        this.treatmentBean = treatmentBean;
    }

    public void add(TreatmentFollowupBean treatmentFollowupBean) {
        treatmentFollowupList.add(treatmentFollowupBean);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return treatmentFollowupList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return treatmentFollowupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (position == 0) {
            convertView = View.inflate(context, R.layout.layout_treatment_info_top, null);
            tvDoctorName = (TextView) convertView.findViewById(R.id.tvDoctorName);
            tvAdministrative = (TextView) convertView.findViewById(R.id.tvAdministrative);
            tvProfessional = (TextView) convertView.findViewById(R.id.tvProfessional);
            tvTreatmentDate = (TextView) convertView.findViewById(R.id.tvTreatmentDate);
            tvTreatmentHospital = (TextView) convertView.findViewById(R.id.tvTreatmentHospital);
            tvPatientName = (TextView) convertView.findViewById(R.id.tvPatientName);
            tvTreatmentReason = (TextView) convertView.findViewById(R.id.tvTreatmentReason);
            ivDoctorHead = (ImageView) convertView.findViewById(R.id.ivDoctorHead);

            tvDoctorName.setText(treatmentBean.getDoctorBean().getUsername());
            tvAdministrative.setText(treatmentBean.getDoctorBean().getAdministrative());
            tvProfessional.setText(treatmentBean.getDoctorBean().getProfessional());
            tvTreatmentDate.setText(treatmentBean.getDate());
            tvTreatmentHospital.setText(treatmentBean.getDoctorBean().getHospital().getName());
            tvPatientName.setText(treatmentBean.getPatientBean().getUsername());
            tvTreatmentReason.setText(treatmentBean.getTreatmentReason());

//            MyLoader.dispalyFromAssets(treatmentBean.getDoctorBean().getHeadUrl(), ivDoctorHead);
        } else {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_treatment_followup, null);
                viewHolder = new ViewHolder();
                viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            TreatmentFollowupBean treatmentFollowupBean = treatmentFollowupList.get(position - 1);

            viewHolder.tvDate.setText(treatmentFollowupBean.getDate());
            viewHolder.tvTitle.setText("诊后第" + position + "次随访");
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tvDate;
        public TextView tvTitle;
    }
}
