package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 描述：就诊记录实体类，一次就诊记录对应多个诊后随访
 * 备注：病人+医生+日期决定一次就诊记录，在这次就诊之后，会延伸多次随访记录
 * <p/>
 * 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentBean implements Serializable {

    private PatientBean patientBean; //就诊病人
    private DoctorBean doctorBean; //负责此次就诊的医生
    private String date; //就诊的日期
    private String treatmentReason; //诊断原因
    private ArrayList<TreatmentFollowupBean> treatmentFollowupList; //该就诊的随访列表

    public TreatmentBean(String date, DoctorBean doctorBean,
                         PatientBean patientBean,
                         String treatmentReason) {
        this.date = date;
        this.doctorBean = doctorBean;
        this.patientBean = patientBean;
        this.treatmentReason = treatmentReason;
        treatmentFollowupList = new ArrayList<TreatmentFollowupBean>();
    }

    // 添加一条诊后随访记录
    public void addTreatmentFollowup(TreatmentFollowupBean treatmentFollowupBean) {
        treatmentFollowupList.add(treatmentFollowupBean);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DoctorBean getDoctorBean() {
        return doctorBean;
    }

    public void setDoctorBean(DoctorBean doctorBean) {
        this.doctorBean = doctorBean;
    }

    public PatientBean getPatientBean() {
        return patientBean;
    }

    public void setPatientBean(PatientBean patientBean) {
        this.patientBean = patientBean;
    }

    public String getTreatmentReason() {
        return treatmentReason;
    }

    public void setTreatmentReason(String treatmentReason) {
        this.treatmentReason = treatmentReason;
    }

    public ArrayList<TreatmentFollowupBean> getTreatmentFollowupList() {
        return treatmentFollowupList;
    }

    public void setTreatmentFollowupList(ArrayList<TreatmentFollowupBean> treatmentFollowupList) {
        this.treatmentFollowupList = treatmentFollowupList;
    }


}
