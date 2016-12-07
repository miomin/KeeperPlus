package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.TreatmentBean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.ITreatmentListPatientPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.ITreatmentListPatientView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by miomin on 16/11/16.
 */

public class TreatmentListPatientPresenter extends BasePresenter<ITreatmentListPatientView> implements ITreatmentListPatientPresenter {

    public TreatmentListPatientPresenter(ITreatmentListPatientView iTreatmentListPatientView) {
        attachView(iTreatmentListPatientView);
    }

    @Override
    public void initTreatmentData() {
        mvpView.showLoading("提示", "请稍后，正在加载数据");
        BmobQuery<TreatmentBean> query;
        query = new BmobQuery<>();
        query.addWhereEqualTo("patient", KeeperPlusCache.getInstance().getCurrentUser());
        query.include("patient");
        query.include("doctor");
        query.findObjects(new FindListener<TreatmentBean>() {
            @Override
            public void done(List<TreatmentBean> list, BmobException e) {
                if (e == null) {
                    for (TreatmentBean treatment : list)
                        if (treatment != null) {
                            treatment.setPatientBean(KeeperPlusCache.getInstance().getCurrentUser());
                            mvpView.addTreatment(treatment);
                        }
                } else {
                    mvpView.showToast("对不起，您的网络不稳定...");
                }
                mvpView.hideLoading();
            }
        });
    }
}
