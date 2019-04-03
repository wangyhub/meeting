package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: meeting
 * @Date: 2019/3/18 16:45
 * @Author: wangy
 * @Description: 校验会议码的model
 */
public class InviteCodeModel implements Serializable {
    private String id;
    private String meetingId;
    private String meetingName;
    private String phone;
    private Date date;
    private String sponsor;
    private String startTime;
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
}
