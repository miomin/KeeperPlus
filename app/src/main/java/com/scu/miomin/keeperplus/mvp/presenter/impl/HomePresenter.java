package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.IHomePresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IHomeView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by miomin on 16/11/16.
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    public HomePresenter(IHomeView iHomeView) {
        attachView(iHomeView);
    }

    @Override
    public void initFriendList() {
        if (KeeperPlusCache.getInstance().getFriendCount() > 0) {
            return;
        }
        mvpView.showLoading("提示", "正在加载数据，请稍后...");

        BmobQuery<Userbean> query = new BmobQuery<>();
        query.addWhereEqualTo("sex", 0);
        query.include("birthday");
        query.include("hospital");
        query.findObjects(new FindListener<Userbean>() {
            @Override
            public void done(List<Userbean> list, BmobException e) {
                if (e == null)
                    for (Userbean user : list)
                        KeeperPlusCache.getInstance().addFriend(user);
                mvpView.hideLoading();
            }
        });
    }
}
