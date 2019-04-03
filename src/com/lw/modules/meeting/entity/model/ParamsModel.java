package com.lw.modules.meeting.entity.model;

import com.lw.modules.meeting.common.AppConstant;

/**
 * @program: meeting
 * @Date: 2019/3/14 10:39
 * @Author: wangy
 * @Description: 带分页的参数对象模型
 */
public class ParamsModel {
    private String userId;
    private String phone;
    private String code;
    private String meetingId;
    private String messageId;
    private Integer startNum;
    private Integer endNum;
    private Integer currentPage;
    private String status;
    private Integer type;

    public ParamsModel() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        if(this.currentPage != null){
            if(this.currentPage <= 0){
                this.currentPage = 1;
            }
            this.startNum = (this.currentPage - 1) * AppConstant.APP_MESSAGE_PAGENUM +1;
            this.endNum = this.currentPage * AppConstant.APP_MESSAGE_PAGENUM;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
