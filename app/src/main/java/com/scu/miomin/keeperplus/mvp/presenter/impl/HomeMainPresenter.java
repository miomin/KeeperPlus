package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.adapter.RemenDoctorAdapter;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.IHomeMainPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeMainView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
        KeeperPlusCache.getInstance().setRemenDoctorAdapter(new RemenDoctorAdapter(mvpView.getViewActivity()));
        // 将ListView与适配器关联
        mvpView.setRemenDoctorAdapter(KeeperPlusCache.getInstance().getRemenDoctorAdapter());
        initRemenDoctorList();
    }

    @Override
    public void showDoctorInfo(int position) {

    }

    private void initRemenDoctorList() {
        if (KeeperPlusCache.getInstance().getRemenDoctorCount() > 0) {
            KeeperPlusCache.getInstance().refreshRemenDoctorAdapter();
            return;
        }

        mvpView.showLoading("提示", "请稍后，正在加载数据...");

        BmobQuery<Userbean> query;
        query = new BmobQuery<>();
        query.addWhereEqualTo("Role", 2);
        query.include("birthday");
        query.include("hospital");
        query.findObjects(new FindListener<Userbean>() {
            @Override
            public void done(List<Userbean> list, BmobException e) {
                if (e == null)
                    KeeperPlusCache.getInstance().addRemenDoctor(list);
                mvpView.hideLoading();
            }
        });
    }
}
