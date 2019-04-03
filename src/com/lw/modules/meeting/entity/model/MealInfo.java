package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

public class MealInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mealId;
    private String mealName;
    private String mealAddress;
    private String mealPhone;
    private Date mealDate;
    private String mealType;
    private String mealKind;
    private String isTable;
    private String meetingId;
    private String tableNum;

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealAddress() {
        return mealAddress;
    }

    public void setMealAddress(String mealAddress) {
        this.mealAddress = mealAddress;
    }

    public String getMealPhone() {
        return mealPhone;
    }

    public void setMealPhone(String mealPhone) {
        this.mealPhone = mealPhone;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealKind() {
        return mealKind;
    }

    public void setMealKind(String mealKind) {
        this.mealKind = mealKind;
    }

    public String getIsTable() {
        return isTable;
    }

    public void setIsTable(String isTable) {
        this.isTable = isTable;
    }
}
