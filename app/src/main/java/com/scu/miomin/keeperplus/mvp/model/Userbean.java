package com.scu.miomin.keeperplus.mvp.model;

import com.scu.miomin.keeperplus.mvp.model.Enum.UserTypeEnum;

import java.io.Serializable;


/**
 * 描述:医生和病人的父类 创建日期:2015/11/2
 *
 * @author 莫绪旻
 */
public class Userbean implements Serializable {

    private String account;// 注册时的手机号，唯一标识
    private String password;// 用户密码
    private String name;// 病人姓名
    private int sex;// 病人性别
    private BirthdayBean birthday;// 病人生日
    private int age;// 病人年龄
    private String headUrl;// 头像在服务端的url

    public Userbean(String account, String password, String name, int sex, BirthdayBean birthday, String headUrl) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.age = calAge(birthday);
        this.headUrl = headUrl;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAge() {
        return age;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public BirthdayBean getBirthday() {
        return birthday;
    }

    public void setBirthday(BirthdayBean birthday) {
        this.birthday = birthday;
        this.age = calAge(birthday);
    }

    private int calAge(BirthdayBean birthday) {
        return 2015 - birthday.getYear();
    }

    public int getUserType() {
        if (account.length() == 11)
            return UserTypeEnum.PATIENT;
        else
            return UserTypeEnum.DOCTOR;
    }

    @Override
    public String toString() {
        return "Userbean{" +
                "phonenumber='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", age=" + age +
                '}';
    }
}
