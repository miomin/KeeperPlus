package com.scu.miomin.keeperplus.mvp.model;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;


/**
 * 描述:医生和病人的父类 创建日期:2015/11/2
 *
 * @author 莫绪旻
 */
public class Userbean extends BmobUser implements Serializable {

    private Integer Role; //1表示病人，2表示医生

    private Integer sex;// 病人性别
    private BirthdayBean birthday;// 病人生日
    private Integer age;// 病人年龄
    private String headUrl;// 头像在服务端的url

    private String professional;  //医生职称，见枚举类ProfessionalEnum
    private String administrative; //医生所在科室，见枚举类AdministrativeEnum
    private HospitalBean hospital; //医生所在医院
    private String introduction; //医生简介

    // 病人身高
    private Double pheight;
    // 病人体重
    private Double pweight;

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative;
    }

    public int getAge() {
        return age;
    }

    public BirthdayBean getBirthday() {
        return birthday;
    }

    public void setBirthday(BirthdayBean birthday) {
        this.birthday = birthday;
        this.age = calAge(birthday);
    }

    private int calAge(BirthdayBean birthday) {
        return 2016 - birthday.getYear();
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public HospitalBean getHospital() {
        return hospital;
    }

    public void setHospital(HospitalBean hospital) {
        this.hospital = hospital;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getPheight() {
        return pheight;
    }

    public void setPheight(double pheight) {
        this.pheight = pheight;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public double getPweight() {
        return pweight;
    }

    public void setPweight(double pweight) {
        this.pweight = pweight;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer role) {
        Role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isDoctor() {
        if (Role == 2)
            return true;
        else
            return false;
    }

    public boolean isPatient() {
        if (Role == 1)
            return true;
        else
            return false;
    }
}
