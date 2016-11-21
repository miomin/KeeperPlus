package com.scu.miomin.keeperplus.mvp.module;

import java.io.Serializable;

/**
 * 描述:病人实体类 创建日期:2015/11/2
 *
 * @author 莫绪旻
 */
public class PatientBean extends Userbean implements Serializable {

    // 病人身高
    private double pheight;
    // 病人体重
    private double pweight;

    public PatientBean(String phonenumber, String password, String name, int sex, BirthdayBean birthday, String headUrl, double pheight, double pweight) {
        super(phonenumber, password, name, sex, birthday, headUrl);
        this.pheight = pheight;
        this.pweight = pweight;
    }

    public double getPheight() {
        return pheight;
    }

    public void setPheight(double pheight) {
        this.pheight = pheight;
    }

    public double getPweight() {
        return pweight;
    }

    public void setPweight(double pweight) {
        this.pweight = pweight;
    }

    @Override
    public String toString() {
        return "PatientBean{" +
                "pheight=" + pheight +
                ", pweight=" + pweight +
                '}' + super.toString();
    }
}
