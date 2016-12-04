package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 描述:表示地图上一个位置的实体类 创建日期:2015/11/13
 *
 * @author 莫绪旻
 */
public class MyLocationBean extends BmobObject implements Serializable {

    private Float longitude; //经度
    private Float latitude; //纬度
    private String address = ""; //医院所在地点位置

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
