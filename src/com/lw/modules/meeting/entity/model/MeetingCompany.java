package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

public class MeetingCompany implements Serializable {
    private static final long serialVersionUID = 1L;
    private String companyId;
    private String meetingId;
    private String companyName;

    public String getCompanyId() {
        return companyId;
    }

    public void setId(String companyId) {
        this.companyId = companyId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
