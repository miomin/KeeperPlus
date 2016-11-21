package com.scu.miomin.keeperplus.mvp.module;

import java.io.Serializable;

/**
 * 描述:表示地图上一个位置的实体类 创建日期:2015/11/13
 *
 * @author 莫绪旻
 */
public class MyLocationBean implements Serializable {

    float longitude; //经度
    float latitude; //纬度

    public MyLocationBean(float longitude, float latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
