package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

public class TbSeatContent implements Serializable {

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    private String seatId;        //会场ID
    private String meetingId;		// 会议ID
    private String meetingName;   //会议名称
    private String placeName;		// 会场名称

    private String pointX;		// X坐标
    private String pointY;		// Y坐标
    private Long platform;		// 主席台
    private String region;		// 参会席
    private Long regionRow;		// 参会席行
    private Date startTime;		// 开始时间
    private Date endTime;		// 结束时间
    private String meetingAddress;  //会议地址


    private String beginDate;  //开始时间
    private String subject; //主题
    private String seatNo;          //座位号
    private String placeAddress;		// 会场地址

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
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

    public Long getPlatform() {
        return platform;
    }

    public void setPlatform(Long platform) {
        this.platform = platform;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getRegionRow() {
        return regionRow;
    }

    public void setRegionRow(Long regionRow) {
        this.regionRow = regionRow;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
