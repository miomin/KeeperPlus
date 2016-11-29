package com.scu.miomin.keeperplus.mvp.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    // 病人云端心电图库
    private List<ECGRecordBean> ecgRecords = new ArrayList<>();

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

    public List<ECGRecordBean> getEcgRecords() {
        return ecgRecords;
    }

    public void setEcgRecords(List<ECGRecordBean> ecgRecords) {
        this.ecgRecords = ecgRecords;
    }

    public void addEcg(ECGRecordBean ecgRecord) {
        this.ecgRecords.add(ecgRecord);
    }

    public void deleteEcg(ECGRecordBean ecgRecord) {
        this.ecgRecords.remove(ecgRecord);
    }

    @Override
    public String toString() {
        return "PatientBean{" +
                "pheight=" + pheight +
                ", pweight=" + pweight +
                '}' + super.toString();
    }
}
