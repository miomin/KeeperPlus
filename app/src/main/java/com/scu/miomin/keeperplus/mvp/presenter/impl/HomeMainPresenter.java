package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.adapter.RemenDoctorAdapter;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.DoctorBean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.IHomeMainPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeMainView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

import java.util.ArrayList;

/**
 * Created by miomin on 16/11/16.
 */

public class HomeMainPresenter extends BasePresenter<IHomeMainView> implements IHomeMainPresenter {

    public HomeMainPresenter(IHomeMainView iHomeMainView) {
        attachView(iHomeMainView);
    }

    @Override
    public void initRemenDoctorAdapter() {
        // 创建适配器对象
        RemenDoctorAdapter remenDoctorAdapter = new RemenDoctorAdapter(new ArrayList<DoctorBean>(), mvpView.getViewActivity());
        // 将ListView与适配器关联
        mvpView.setRemenDoctorAdapter(remenDoctorAdapter);
        remenDoctorAdapter.add(KeeperPlusCache.getInstance().getRemenDoctorArray());
    }

    @Override
    public void initRemenDoctorListener() {
//        lvRemenDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                if (position == 1)
//                    return;
//
////                DoctorInfoActivity.actionStart(MainKeeperForPatient.this, remendoctorArray.get(position - 2));
//            }
//        });
    }
}
