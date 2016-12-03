package com.scu.miomin.keeperplus.mvp.presenter.impl;

import com.scu.miomin.keeperplus.adapter.ECGRecordAdapter;
import com.scu.miomin.keeperplus.core.KeepPlusApp;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.ECGRecordBean;
import com.scu.miomin.keeperplus.mvp.model.Enum.UserTypeEnum;
import com.scu.miomin.keeperplus.mvp.model.HealthyDescribeByPatientBean;
import com.scu.miomin.keeperplus.mvp.presenter.interf.IECGRecordPresenter;
import com.scu.miomin.keeperplus.mvp.view.interf.IECGRecordView;
import com.scu.miomin.keeperplus.mvpcore.BasePresenter;
import com.scu.miomin.keeperplus.util.ecg.ECGDirSaveUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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

            if (KeeperPlusCache.getInstance().getCurrentUser().getUserType() == UserTypeEnum.PATIENT) {
                if (i < 2)
                    isAtPhone = false;
                else
                    isAtPhone = true;
            } else if (KeeperPlusCache.getInstance().getCurrentUser().getUserType() == UserTypeEnum.DOCTOR) {
                isAtPhone = true;
            }

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
    public void uploadECGFile(String filename) {
        try {
            FileInputStream in = mvpView.getContext().openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
