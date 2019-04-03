package com.lw.modules.meeting.entity.model;

import java.io.Serializable;
import java.util.Date;

public class JoinInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;		// 用户ID
    private String meetingId;		// 会议ID
    private String joinType;		// 参加类型
    private String companyId;		// 单位
    private String position;		// 职务
    private String inviteCode;		// 邀请码
    private String isComeStation;		// 是否接站
    private String comeNumber;		// 到达航班/车次
    private String comeStation;		// 到达站
    private Date comeTime;		// 到达时间
    private String comeAddress;		// 接站地址
    private String isSendStation;		// 是否送站
    private String goNumber;		// 离开航班/车次
    private String goStation;		// 离开站
    private Date goTime;		// 离开时间
    private String goAddress;		// 送站地址
    private String isLive;		// 是否住宿

    //add
    private String userName;		// 姓名
    private String sex;				// 性别
    private String nation;			// 名族
    private String phone;			// 手机
    private String email;			// 邮箱
    private String companyName;		//单位名称

    private String createBy;
    private Date createDate;

    private String updateBy;
    private Date updateDate;

    private String comeTime1;
    private String goTime1;

    public String getComeTime1() {
        return comeTime1;
    }

    public void setComeTime1(String comeTime1) {
        this.comeTime1 = comeTime1;
    }

    public String getGoTime1() {
        return goTime1;
    }

    public void setGoTime1(String goTime1) {
        this.goTime1 = goTime1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getIsComeStation() {
        return isComeStation;
    }

    public void setIsComeStation(String isComeStation) {
        this.isComeStation = isComeStation;
    }

    public String getComeNumber() {
        return comeNumber;
    }

    public void setComeNumber(String comeNumber) {
        this.comeNumber = comeNumber;
    }

    public String getComeStation() {
        return comeStation;
    }

    public void setComeStation(String comeStation) {
        this.comeStation = comeStation;
    }

    public Date getComeTime() {
        return comeTime;
    }

    public void setComeTime(Date comeTime) {
        this.comeTime = comeTime;
    }

    public String getComeAddress() {
        return comeAddress;
    }

    public void setComeAddress(String comeAddress) {
        this.comeAddress = comeAddress;
    }

    public String getIsSendStation() {
        return isSendStation;
    }

    public void setIsSendStation(String isSendStation) {
        this.isSendStation = isSendStation;
    }

    public String getGoNumber() {
        return goNumber;
    }

    public void setGoNumber(String goNumber) {
        this.goNumber = goNumber;
    }

    public String getGoStation() {
        return goStation;
    }

    public void setGoStation(String goStation) {
        this.goStation = goStation;
    }

    public Date getGoTime() {
        return goTime;
    }

    public void setGoTime(Date goTime) {
        this.goTime = goTime;
    }

    public String getGoAddress() {
        return goAddress;
    }

    public void setGoAddress(String goAddress) {
        this.goAddress = goAddress;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
