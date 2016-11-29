package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

/**
 * 描述：诊后随访实体类 创建日期:2015/11/22
 *
 * @author 莫绪旻
 */
public class TreatmentFollowupBean implements Serializable {

    private String date; //随访日期
    /**
     * 下面是检查/化验的数据
     */
    private double RUSUANTUOQINGMEI = -1; //乳酸脱氢酶
    private double GUCAOZHUANANMEI = -1; //谷草转氨酶
    private double GUBINGZHUANANMEI = -1; //谷丙转氨酶
    private double LINGSUANJISUANJIMEI = -1; //磷酸肌酸激酶
    private double LINGSUANJISUANJIMEITONGGONGMEI = -1; //磷酸肌酸激酶同工酶
    private double JIHONGDANBAI = -1; //肌红蛋白
    private double JIGAIDANBAI = -1; //肌钙蛋白
    private double WAIZHOUXUEHONGXIBAOZONGSHU = -1; //外周血红细胞总数
    private double ZHONGXINGLIXIBAOBILV = -1; //中性粒细胞比率
    private String describeByDoctor; //医生对此次复诊的描述
    private String describeByPatient; //病人自我感觉的描述

    public TreatmentFollowupBean(String date) {
        this.date = date;
    }

    public TreatmentFollowupBean(String date, String describeByDoctor, String describeByPatient,
                                 double GUBINGZHUANANMEI, double GUCAOZHUANANMEI, double JIGAIDANBAI,
                                 double JIHONGDANBAI, double LINGSUANJISUANJIMEI,
                                 double LINGSUANJISUANJIMEITONGGONGMEI, double RUSUANTUOQINGMEI,
                                 double WAIZHOUXUEHONGXIBAOZONGSHU, double ZHONGXINGLIXIBAOBILV) {
        this.date = date;
        this.describeByDoctor = describeByDoctor;
        this.describeByPatient = describeByPatient;
        this.GUBINGZHUANANMEI = GUBINGZHUANANMEI;
        this.GUCAOZHUANANMEI = GUCAOZHUANANMEI;
        this.JIGAIDANBAI = JIGAIDANBAI;
        this.JIHONGDANBAI = JIHONGDANBAI;
        this.LINGSUANJISUANJIMEI = LINGSUANJISUANJIMEI;
        this.LINGSUANJISUANJIMEITONGGONGMEI = LINGSUANJISUANJIMEITONGGONGMEI;
        this.RUSUANTUOQINGMEI = RUSUANTUOQINGMEI;
        this.WAIZHOUXUEHONGXIBAOZONGSHU = WAIZHOUXUEHONGXIBAOZONGSHU;
        this.ZHONGXINGLIXIBAOBILV = ZHONGXINGLIXIBAOBILV;
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
        return GUBINGZHUANANMEI;
    }

    public void setGUBINGZHUANANMEI(double GUBINGZHUANANMEI) {
        this.GUBINGZHUANANMEI = GUBINGZHUANANMEI;
    }

    public double getGUCAOZHUANANMEI() {
        return GUCAOZHUANANMEI;
    }

    public void setGUCAOZHUANANMEI(double GUCAOZHUANANMEI) {
        this.GUCAOZHUANANMEI = GUCAOZHUANANMEI;
    }

    public double getJIGAIDANBAI() {
        return JIGAIDANBAI;
    }

    public void setJIGAIDANBAI(double JIGAIDANBAI) {
        this.JIGAIDANBAI = JIGAIDANBAI;
    }

    public double getJIHONGDANBAI() {
        return JIHONGDANBAI;
    }

    public void setJIHONGDANBAI(double JIHONGDANBAI) {
        this.JIHONGDANBAI = JIHONGDANBAI;
    }

    public double getLINGSUANJISUANJIMEI() {
        return LINGSUANJISUANJIMEI;
    }

    public void setLINGSUANJISUANJIMEI(double LINGSUANJISUANJIMEI) {
        this.LINGSUANJISUANJIMEI = LINGSUANJISUANJIMEI;
    }

    public double getLINGSUANJISUANJIMEITONGGONGMEI() {
        return LINGSUANJISUANJIMEITONGGONGMEI;
    }

    public void setLINGSUANJISUANJIMEITONGGONGMEI(double LINGSUANJISUANJIMEITONGGONGMEI) {
        this.LINGSUANJISUANJIMEITONGGONGMEI = LINGSUANJISUANJIMEITONGGONGMEI;
    }

    public double getRUSUANTUOQINGMEI() {
        return RUSUANTUOQINGMEI;
    }

    public void setRUSUANTUOQINGMEI(double RUSUANTUOQINGMEI) {
        this.RUSUANTUOQINGMEI = RUSUANTUOQINGMEI;
    }

    public double getWAIZHOUXUEHONGXIBAOZONGSHU() {
        return WAIZHOUXUEHONGXIBAOZONGSHU;
    }

    public void setWAIZHOUXUEHONGXIBAOZONGSHU(double WAIZHOUXUEHONGXIBAOZONGSHU) {
        this.WAIZHOUXUEHONGXIBAOZONGSHU = WAIZHOUXUEHONGXIBAOZONGSHU;
    }

    public double getZHONGXINGLIXIBAOBILV() {
        return ZHONGXINGLIXIBAOBILV;
    }

    public void setZHONGXINGLIXIBAOBILV(double ZHONGXINGLIXIBAOBILV) {
        this.ZHONGXINGLIXIBAOBILV = ZHONGXINGLIXIBAOBILV;
    }
}
