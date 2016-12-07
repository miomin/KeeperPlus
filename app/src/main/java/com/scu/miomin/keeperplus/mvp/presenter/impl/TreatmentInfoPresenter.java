package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.model.TreatmentFollowup;
import com.scu.miomin.keeperplus.mvp.presenter.interf.ITreatmentInfoPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentInfoView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by miomin on 16/11/16.
 */

public class TreatmentInfoPresenter extends BasePresenter<ITreatmentInfoView> implements ITreatmentInfoPresenter {

    public TreatmentInfoPresenter(ITreatmentInfoView iTreatmentInfoView) {
        attachView(iTreatmentInfoView);
    }

    @Override
    public void initTreatmentFollowUpData(TreatmentBean treatmentBean) {
        mvpView.showLoading("提示", "请稍后，正在加载数据");
        BmobQuery<TreatmentFollowup> query;
        query = new BmobQuery<>();
        query.addWhereEqualTo("treatment", treatmentBean);
        query.include("treatment");
        query.findObjects(new FindListener<TreatmentFollowup>() {
            @Override
            public void done(List<TreatmentFollowup> list, BmobException e) {
                if (e == null) {
                    for (TreatmentFollowup treatmentFollowup : list)
                        if (treatmentFollowup != null)
                            mvpView.addTreatmentFollowup(treatmentFollowup);
                } else {
                    mvpView.showToast("对不起，您的网络不稳定...");
                }
                mvpView.hideLoading();
            }
        });
    }
}
