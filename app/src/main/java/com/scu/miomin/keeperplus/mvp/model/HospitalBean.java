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
    private MyLocationBean myLocationBean; //医院位置

    public HospitalBean(String name, String province, String city, String county, MyLocationBean myLocationBean) {
        this.name = name;
        this.province = province;
        this.city = city;
        this.county = county;
        this.myLocationBean = myLocationBean;
    }

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

    public MyLocationBean getMyLocationBean() {
        return myLocationBean;
    }

    public void setMyLocationBean(MyLocationBean myLocationBean) {
        this.myLocationBean = myLocationBean;
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
