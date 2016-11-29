package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

/**
 * 描述:心电图记录实体类，表示存储在本地或云端的一份心电图，包含心电图文件和日期 创建日期:2015/11/11
 *
 * @author 莫绪旻
 */
public class ECGRecordBean implements Serializable {

    private String date; //测心电图的日期
    private boolean isLocality; //心电图是不是被同步到本地，在云端为false，在本地为true
    private String fileName; //心电图对应的文件的名字
    private HealthyDescribeByPatientBean healthyDescribeByMyself; //测心电图前病人填写的病情自述

    public ECGRecordBean(String date, String fileName, HealthyDescribeByPatientBean healthyDescribeByMyself, boolean isLocality) {
        this.date = date;
        this.fileName = fileName;
        this.healthyDescribeByMyself = healthyDescribeByMyself;
        this.isLocality = isLocality;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFilename() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HealthyDescribeByPatientBean getHealthyDescribeByMyself() {
        return healthyDescribeByMyself;
    }

    public void setHealthyDescribeByMyself(HealthyDescribeByPatientBean healthyDescribeByMyself) {
        this.healthyDescribeByMyself = healthyDescribeByMyself;
    }

    public boolean isLocality() {
        return isLocality;
    }

    public void setisLocality(boolean isLocality) {
        this.isLocality = isLocality;
    }
}
