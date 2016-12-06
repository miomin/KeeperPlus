package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 描述：就诊记录实体类，一次就诊记录对应多个诊后随访
 * 备注：病人+医生+日期决定一次就诊记录，在这次就诊之后，会延伸多次随访记录
 * <p/>
 * 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentBean extends BmobObject implements Serializable {

    private Userbean patient; //就诊病人
    private Userbean doctor; //负责此次就诊的医生
    private String date; //就诊的日期
    private String treatmentReason; //诊断原因

    public TreatmentBean(String date, Userbean doctorBean,
                         Userbean patientBean,
                         String treatmentReason) {
        this.date = date;
        this.doctor = doctorBean;
        this.patient = patientBean;
        this.treatmentReason = treatmentReason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Userbean getDoctorBean() {
        return doctor;
    }

    public void setDoctorBean(Userbean doctor) {
        this.doctor = doctor;
    }

    public Userbean getPatientBean() {
        return patient;
    }

    public void setPatientBean(Userbean patient) {
        this.patient = patient;
    }

    public String getTreatmentReason() {
        return treatmentReason;
    }

    public void setTreatmentReason(String treatmentReason) {
        this.treatmentReason = treatmentReason;
    }
}
