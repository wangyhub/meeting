package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

public class AgendaDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String agendaId;		// 议程ID 父类
    private String agendaTime;		// 时间
    private String subject;		// 主题
    private String content;		// 内容

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getAgendaTime() {
        return agendaTime;
    }

    public void setAgendaTime(String agendaTime) {
        this.agendaTime = agendaTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}