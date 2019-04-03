/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.lw.common.persistence.DataEntity;

/**
 * 会议管理Entity
 * @author meijx
 * @version 2019-03-06
 */
public class TbMeeting extends DataEntity<TbMeeting> {
	
	private static final long serialVersionUID = 1L;
	private String meetingName;		// 会议名称
	private String sponsor;		// 主办方
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String meetingAddress;		// 会议地址
	private Long personNum;		// 会议人数
	private String createCode;		// 会议创建码
	private String isStation;		// 接送安排
	private String isLive;		// 住宿安排
	private String isMeal;		// 用餐安排
	private String isSignin;		// 签到管理
	private String isNews;		// 动态发布
	private String status;		// 状态
	private List<TbMeetingCompany> tbMeetingCompanyList = Lists.newArrayList();		// 子表列表

	private String pointX;		// X坐标
	private String pointY;		// Y坐标
	
	public TbMeeting() {
		super();
	}

	public TbMeeting(String id){
		super(id);
	}

	@Length(min=0, max=100, message="会议名称长度必须介于 0 和 100 之间")
	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	
	@Length(min=0, max=100, message="主办方长度必须介于 0 和 100 之间")
	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=100, message="会议地址长度必须介于 0 和 100 之间")
	public String getMeetingAddress() {
		return meetingAddress;
	}

	public void setMeetingAddress(String meetingAddress) {
		this.meetingAddress = meetingAddress;
	}
	
	public Long getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Long personNum) {
		this.personNum = personNum;
	}
	
	@Length(min=0, max=40, message="会议创建码长度必须介于 0 和 40 之间")
	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}
	
	@Length(min=0, max=2, message="接送安排长度必须介于 0 和 2 之间")
	public String getIsStation() {
		return isStation;
	}

	public void setIsStation(String isStation) {
		this.isStation = isStation;
	}
	
	@Length(min=0, max=2, message="住宿安排长度必须介于 0 和 2 之间")
	public String getIsLive() {
		return isLive;
	}

	public void setIsLive(String isLive) {
		this.isLive = isLive;
	}
	
	@Length(min=0, max=2, message="用餐安排长度必须介于 0 和 2 之间")
	public String getIsMeal() {
		return isMeal;
	}

	public void setIsMeal(String isMeal) {
		this.isMeal = isMeal;
	}
	
	@Length(min=0, max=2, message="签到管理长度必须介于 0 和 2 之间")
	public String getIsSignin() {
		return isSignin;
	}

	public void setIsSignin(String isSignin) {
		this.isSignin = isSignin;
	}
	
	@Length(min=0, max=2, message="动态发布长度必须介于 0 和 2 之间")
	public String getIsNews() {
		return isNews;
	}

	public void setIsNews(String isNews) {
		this.isNews = isNews;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<TbMeetingCompany> getTbMeetingCompanyList() {
		return tbMeetingCompanyList;
	}

	public void setTbMeetingCompanyList(List<TbMeetingCompany> tbMeetingCompanyList) {
		this.tbMeetingCompanyList = tbMeetingCompanyList;
	}

	@Length(min=0, max=20, message="X坐标长度必须介于 0 和 20 之间")
	public String getPointX() {
		return pointX;
	}

	public void setPointX(String pointX) {
		this.pointX = pointX;
	}

	@Length(min=0, max=20, message="Y坐标长度必须介于 0 和 20 之间")
	public String getPointY() {
		return pointY;
	}

	public void setPointY(String pointY) {
		this.pointY = pointY;
	}
}