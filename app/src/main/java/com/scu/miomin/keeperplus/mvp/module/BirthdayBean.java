package com.scu.miomin.keeperplus.mvp.module;

import java.io.Serializable;

/**
 * 描述:生日实体类 创建日期:2015/11/3
 *
 * @author 莫绪旻
 */
public class BirthdayBean implements Serializable {

    private int year;
    private int month;
    private int day;

    public BirthdayBean(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
