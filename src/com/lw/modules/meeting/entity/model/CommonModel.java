package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

/**
 * @program: meeting
 * @Date: 2019/3/19 13:14
 * @Author: wangy
 * @Description:
 */
public class CommonModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String meetingName;
    private String startTime;
    private String endTime;
    private String meetingAddress;
    private String pointX;		// X坐标
    private String pointY;		// Y坐标
    private String smsCode;
    private String phone;

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getPointX() {
        return pointX;
    }

    public void setPointX(String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return pointY;
    }

    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
