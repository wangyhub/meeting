package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

/**
 * @program: meeting
 * @Date: 2019/3/13 15:00
 * @Author: wangy
 * @Description: 手机登录验证之后返回的数据对象
 */
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;          //用户id
    private String phone;       //登录手机号
    private String userName;    //用户姓名
    private String inviteCode;  //会议邀请码
    private String meetingId;   //会议ID
    private String meetingName; //首页显示的会议名称

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
}

