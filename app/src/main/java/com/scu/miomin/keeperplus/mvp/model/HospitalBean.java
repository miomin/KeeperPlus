package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

/**
 * 描述:医院实体类 创建日期:2015/11/13
 *
 * @author 莫绪旻
 */
public class HospitalBean implements Serializable {

    private String name; //医院名
    private String province; //医院所在省
    private String city; //医院所在城市
    private String county; //医院所在县/区
    private MyLocationBean location; //医院位置

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public MyLocationBean getLocation() {
        return location;
    }

    public void setLocation(MyLocationBean location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
