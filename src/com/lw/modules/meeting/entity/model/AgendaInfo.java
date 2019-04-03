package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AgendaInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String agendaId;      //议程ID
    private String meetingId;		// 会议ID
    private String placeId;		// 会场ID
    private String agendaName;    //议程名称
    private String subject;		// 主题
    private Date beginDate;		// 开始时间
    private Date endDate;		// 结束时间
    private String moderator;		// 主持人
    private String placeName;		// 会场名称

    private List<AgendaDetail> detailList;

    public List<AgendaDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<AgendaDetail> detailList) {
        this.detailList = detailList;
    }

    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}