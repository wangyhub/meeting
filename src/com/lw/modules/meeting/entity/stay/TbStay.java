/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.stay;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

import java.util.Date;

/**
 * 住宿信息Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbStay extends DataEntity<TbStay> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String hotelId;		// 酒店ID
	private Date stayTime;		// 入住时间
	private Date leaveTime;		// 离店时间

	private String hotelName;		// 酒店名称
	private String joinNum;		//参加数量
	
	public TbStay() {
		super();
	}

	public TbStay(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Length(min=0, max=40, message="酒店ID长度必须介于 0 和 40 之间")
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
	@Length(min=0, max=100, message="住宿时间长度必须介于 0 和 100 之间")
	public Date getStayTime() {
		return stayTime;
	}

	public void setStayTime(Date stayTime) {
		this.stayTime = stayTime;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(String joinNum) {
		this.joinNum = joinNum;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
}