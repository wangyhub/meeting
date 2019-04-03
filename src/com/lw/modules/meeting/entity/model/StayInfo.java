package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

public class StayInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stayId;
    private String meetingId;
    private String userId;
    private String hotelId;
    private Date stayTime;
    private Date leaveTime;
    private String hotelName;
    private String hotelAddress;
    private String hotelPhone;
    private String stayNotice;
    private String pointX;
    private String pointY;

    public String getStayId() {
        return stayId;
    }

    public void setStayId(String stayId) {
        this.stayId = stayId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getStayNotice() {
        return stayNotice;
    }

    public void setStayNotice(String stayNotice) {
        this.stayNotice = stayNotice;
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

    public Date getStayTime() {
        return stayTime;
    }

    public void setStayTime(Date stayTime) {
        this.stayTime = stayTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}
