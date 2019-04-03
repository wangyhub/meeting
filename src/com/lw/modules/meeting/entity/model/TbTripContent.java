package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

public class TbTripContent  implements Serializable  {

    private String tripId;        //行程ID
    private String meetingId;		// 会议ID
    private Date tripTime;		// 乘车时间
    private String tripAddress;	// 接送地点
    private String carModel;		// 车型
    private String carNum;		// 车牌号
    private String carMan;		// 联系人
    private String manPhone;		// 联系人电话
    private String remark;        //备注

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public Date getTripTime() {
        return tripTime;
    }

    public void setTripTime(Date tripTime) {
        this.tripTime = tripTime;
    }

    public String getTripAddress() {
        return tripAddress;
    }

    public void setTripAddress(String tripAddress) {
        this.tripAddress = tripAddress;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarMan() {
        return carMan;
    }

    public void setCarMan(String carMan) {
        this.carMan = carMan;
    }

    public String getManPhone() {
        return manPhone;
    }

    public void setManPhone(String manPhone) {
        this.manPhone = manPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
}
