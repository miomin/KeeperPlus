package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.adapter.ECGRecordAdapter;
import com.scu.miomin.keeperplus.core.KeepPlusApp;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.ECGRecordBean;
import com.scu.miomin.keeperplus.mvp.model.HealthyDescribeByPatientBean;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.IECGRecordPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IECGRecordView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;
import com.scu.miomin.keeperplus.util.ecg.ECGDirSaveUtil;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by miomin on 16/11/16.
 */

public class ECGRecordPresenter extends BasePresenter<IECGRecordView> implements IECGRecordPresenter {

    private ArrayList<String> ecgfileNameList;
    private ECGRecordAdapter ecgRecordAdapter;

    public ECGRecordPresenter(IECGRecordView iecgRecordView) {
        attachView(iecgRecordView);
    }

    @Override
    public void readEcgFileDir() {

        ecgfileNameList = ECGDirSaveUtil.readRecordDir(KeepPlusApp.getInstance());

        ECGRecordBean ecgRecord;
        for (int i = 0; i < ecgfileNameList.size(); i++) {

            String temp1[] = ecgfileNameList.get(i).split("_");
            String recordDate = temp1[2];
            String yearStr = recordDate.substring(0, 4);

            String monthStr = recordDate.substring(4, 6);
            if (monthStr.substring(0, 1).equals(0)) {
                monthStr = monthStr.substring(1, 2);
            }

            String dayStr = recordDate.substring(6, 8);
            if (dayStr.substring(0, 1).equals(0)) {
                dayStr = dayStr.substring(1, 2);
            }

            String hourStr = recordDate.substring(8, 10);
            if (hourStr.substring(0, 1).equals(0)) {
                hourStr = hourStr.substring(1, 2);
            }

            String minuteStr = recordDate.substring(10, 12);
            if (minuteStr.substring(0, 1).equals(0)) {
                minuteStr = minuteStr.substring(1, 2);
            }

            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            int day = Integer.parseInt(dayStr);
            int hour = Integer.parseInt(hourStr);
            int minute = Integer.parseInt(minuteStr);

            boolean isAtPhone = false;

            ecgRecord = new ECGRecordBean(year + "-" + month + "-" + day + " " + hour + ":" + minute,
                    ecgfileNameList.get(i), new HealthyDescribeByPatientBean(false, false, false, false, false, false), isAtPhone);
            ecgRecordAdapter.add(ecgRecord);
        }
    }

    @Override
    public void initECGRecordAdapter() {
        // 创建适配器对象
        ecgRecordAdapter = new ECGRecordAdapter(mvpView.getContext());
        // 将ListView与适配器关联
        mvpView.setECGRecordAdapter(ecgRecordAdapter);
    }

    @Override
    public String getClickItemFileName(int index) {
        return ecgfileNameList.get(index);
    }

    @Override
    public void uploadECGFile(File file, final String filename) {
        final BmobFile bmobFile = new BmobFile(file);

        if (bmobFile == null) {
            mvpView.showToast("读取文件失败，没有这个文件");
            return;
        }

        mvpView.showLoading("提示", "请稍等，文件正在上传");
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    mvpView.showToast("上传文件成功:" + bmobFile.getFileUrl());
                    updateClouldECGRecord(filename);
                } else {
                    mvpView.showToast("上传文件失败：" + e.getMessage());
                }
                mvpView.hideLoading();
            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

    private void updateClouldECGRecord(String filename) {
        if (KeeperPlusCache.getInstance().getCurrentUser().getEcgRecords() == null) {
            KeeperPlusCache.getInstance().getCurrentUser().setEcgRecords(new ArrayList<String>());
        }
        KeeperPlusCache.getInstance().getCurrentUser().getEcgRecords().add(filename);

        Userbean userbean = new Userbean();

        userbean.setEcgRecords(KeeperPlusCache.getInstance().getCurrentUser().getEcgRecords());
        userbean.update(KeeperPlusCache.getInstance().getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
            }
        });
    }
}
