package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.adapter.TreatmentListPatientAdapter;
import com.scu.miomin.keeperplus.dao.moke.KeeperDataMoke;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.presenter.interf.ITreatmentListPatientPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentListPatientView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

/**
 * Created by miomin on 16/11/16.
 */

public class TreatmentListPatientPresenter extends BasePresenter<ITreatmentListPatientView> implements ITreatmentListPatientPresenter {

    public TreatmentListPatientPresenter(ITreatmentListPatientView iTreatmentListPatientView) {
        attachView(iTreatmentListPatientView);
    }

    @Override
    public void initTreatmentData() {
        KeeperDataMoke.getInstance().initData();
    }

    @Override
    public void clearTreatmentData() {
        KeeperDataMoke.getInstance().clearData();
    }

    @Override
    public void initTreatmentAdapter() {
        // 创建适配器对象
        KeeperPlusCache.getInstance().setTreatmentListAdapter(new TreatmentListPatientAdapter(mvpView.getContext(), KeeperDataMoke.getInstance().getTreatmentArray()));
        // 将ListView与适配器关联
        mvpView.setTreatmentAdapter(KeeperPlusCache.getInstance().getTreatmentListAdapter());
    }
}
