package com.scu.miomin.keeperplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.view.impl.activity.ECGActivity;
import com.scu.miomin.keeperplus.mvp.view.impl.activity.ECGRecordActivity;
import com.scu.miomin.keeperplus.ui.MyBanner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by miomin on 15/11/22.
 */
public class RemenDoctorAdapter extends BaseAdapter {

    private ArrayList<Userbean> remenDoctorList = new ArrayList<>();
    private Context context;
    private MyBanner myBanner;

    public RemenDoctorAdapter(Context context) {
        this.context = context;
    }

    public void add(Userbean doctorBean) {
        remenDoctorList.add(doctorBean);
        notifyDataSetChanged();
    }

    public void add(List<Userbean> list) {
        remenDoctorList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return remenDoctorList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return remenDoctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (position == 0) {
            convertView = View.inflate(context, R.layout.layout_main_top, null);
            convertView.findViewById(R.id.layout_ecg_monitor).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ECGActivity.startActivity(context);
                }
            });
            convertView.findViewById(R.id.layout_healthy_record).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ECGRecordActivity.startActivity(context);
                }
            });
            myBanner = (MyBanner) convertView.findViewById(R.id.myBanner);
            myBanner.startPlay();
        } else {
            convertView = View.inflate(context, R.layout.item_remendoctor, null);
            viewHolder = new ViewHolder();
            viewHolder.ivHead = (ImageView) convertView.findViewById(R.id.ivHead);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvAdministrative = (TextView) convertView.findViewById(R.id.tvAdministrative);
            viewHolder.tvProfessional = (TextView) convertView.findViewById(R.id.tvProfessional);
            viewHolder.tvHospital = (TextView) convertView.findViewById(R.id.tvHospital);
            viewHolder.tvIntroduction = (TextView) convertView.findViewById(R.id.tvIntroduction);
            convertView.setTag(viewHolder);

            Userbean user = remenDoctorList.get(position - 1);

            viewHolder.tvName.setText(user.getUsername());
            viewHolder.tvAdministrative.setText(user.getAdministrative());
            viewHolder.tvProfessional.setText(user.getProfessional());
            viewHolder.tvHospital.setText(user.getHospital().getName());
            viewHolder.tvIntroduction.setText(user.getIntroduction());

//            MyLoader.dispalyFromAssets(doctorBean.getHeadUrl(),
//                    viewHolder.ivHead);
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView ivHead;
        public TextView tvName;
        public TextView tvAdministrative;
        public TextView tvProfessional;
        public TextView tvHospital;
        public TextView tvIntroduction;
    }
}
