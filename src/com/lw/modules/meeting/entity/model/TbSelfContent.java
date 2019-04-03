package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

public class TbSelfContent implements Serializable {

    private String name; //姓名

    private String sex;  //性别

    private String age;  //年龄

    private String nation; //民族

    private String comPany; //单位

    private String position;  //职务

    private String  phone;  //手机

    private String  emai;   //邮箱

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getComPany() {
        return comPany;
    }

    public void setComPany(String comPany) {
        this.comPany = comPany;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }
}
