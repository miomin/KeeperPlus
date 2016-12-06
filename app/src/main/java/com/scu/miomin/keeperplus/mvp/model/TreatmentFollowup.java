package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 描述：诊后随访实体类 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentFollowup extends BmobObject implements Serializable {

    private TreatmentBean treatment;
    private String date; //随访日期
    /**
     * 下面是检查/化验的数据
     */
    private double HBDH = -1; //乳酸脱氢酶
    private double ALT = -1; //谷草转氨酶
    private double AST = -1; //谷丙转氨酶
    private double CK = -1; //磷酸肌酸激酶
    private double CKMB = -1; //磷酸肌酸激酶同工酶
    private double MB = -1; //肌红蛋白
    private double CTN = -1; //肌钙蛋白
    private double PBCC = -1; //外周血红细胞总数
    private double NEU = -1; //中性粒细胞比率
    private String describeByDoctor; //医生对此次复诊的描述
    private String describeByPatient; //病人自我感觉的描述

    public TreatmentFollowup(String date) {
        this.date = date;
    }

    public TreatmentFollowup(String date, String describeByDoctor, String describeByPatient,
                             double AST, double ALT, double CTN,
                             double MB, double CK,
                             double CKMB, double HBDH,
                             double PBCC, double NEU) {
        this.date = date;
        this.describeByDoctor = describeByDoctor;
        this.describeByPatient = describeByPatient;
        this.AST = AST;
        this.ALT = ALT;
        this.CTN = CTN;
        this.MB = MB;
        this.CK = CK;
        this.CKMB = CKMB;
        this.HBDH = HBDH;
        this.PBCC = PBCC;
        this.NEU = NEU;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescribeByDoctor() {
        return describeByDoctor;
    }

    public void setDescribeByDoctor(String describeByDoctor) {
        this.describeByDoctor = describeByDoctor;
    }

    public String getDescribeByPatient() {
        return describeByPatient;
    }

    public void setDescribeByPatient(String describeByPatient) {
        this.describeByPatient = describeByPatient;
    }

    public double getGUBINGZHUANANMEI() {
        return AST;
    }

    public void setGUBINGZHUANANMEI(double AST) {
        this.AST = AST;
    }

    public double getGUCAOZHUANANMEI() {
        return ALT;
    }

    public void setGUCAOZHUANANMEI(double ALT) {
        this.ALT = ALT;
    }

    public double getJIGAIDANBAI() {
        return CTN;
    }

    public void setJIGAIDANBAI(double CTN) {
        this.CTN = CTN;
    }

    public double getJIHONGDANBAI() {
        return MB;
    }

    public void setJIHONGDANBAI(double MB) {
        this.MB = MB;
    }

    public double getLINGSUANJISUANJIMEI() {
        return CK;
    }

    public void setLINGSUANJISUANJIMEI(double CK) {
        this.CK = CK;
    }

    public double getLINGSUANJISUANJIMEITONGGONGMEI() {
        return CKMB;
    }

    public void setLINGSUANJISUANJIMEITONGGONGMEI(double CKMB) {
        this.CKMB = CKMB;
    }

    public double getRUSUANTUOQINGMEI() {
        return HBDH;
    }

    public void setRUSUANTUOQINGMEI(double HBDH) {
        this.HBDH = HBDH;
    }

    public double getWAIZHOUXUEHONGXIBAOZONGSHU() {
        return PBCC;
    }

    public void setWAIZHOUXUEHONGXIBAOZONGSHU(double PBCC) {
        this.PBCC = PBCC;
    }

    public double getZHONGXINGLIXIBAOBILV() {
        return NEU;
    }

    public void setZHONGXINGLIXIBAOBILV(double NEU) {
        this.NEU = NEU;
    }

    public TreatmentBean getTreatment() {
        return treatment;
    }

    public void setTreatment(TreatmentBean treatment) {
        this.treatment = treatment;
    }
}
