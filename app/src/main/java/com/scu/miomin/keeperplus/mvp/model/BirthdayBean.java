package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 描述:生日实体类 创建日期:2015/11/3
 *
 * @author 莫绪旻
 */
public class BirthdayBean extends BmobObject implements Serializable {

    private Integer year;
    private Integer month;
    private Integer day;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        String birthday = year + "-";

        if (month < 10)
            birthday = birthday + "0" + month + "-";
        else
            birthday = birthday + month + "-";

        if (day < 10)
            birthday = birthday + "0" + day;
        else
            birthday = birthday + day;

        return birthday;
    }
}
