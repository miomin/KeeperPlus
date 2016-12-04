package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.adapter.TreatmentFollowupAdapter;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.ITreatmentInfoPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentInfoView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

/**
 * Created by miomin on 16/11/16.
 */

public class TreatmentInfoPresenter extends BasePresenter<ITreatmentInfoView> implements ITreatmentInfoPresenter {

    public TreatmentInfoPresenter(ITreatmentInfoView iTreatmentInfoView) {
        attachView(iTreatmentInfoView);
    }

    @Override
    public void initTreatmentFollowUpAdapter(TreatmentBean treatmentBean) {
        // 创建适配器对象
        KeeperPlusCache.getInstance().setTreatmentFollowupAdapter(new TreatmentFollowupAdapter(treatmentBean.getTreatmentFollowupList(), mvpView.getContext(), treatmentBean));
        mvpView.setTreatmentInfoAdapter((KeeperPlusCache.getInstance().getTreatmentFollowupAdapter()));
    }
}
