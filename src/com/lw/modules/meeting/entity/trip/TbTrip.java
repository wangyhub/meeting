/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.trip;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.lw.common.persistence.DataEntity;

/**
 * 行程安排Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbTrip extends DataEntity<TbTrip> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String carId;		// 车辆ID
	private Date tripTime;		// 乘车时间
	private String tripAddress;		// 接送地点
	private String callMan;		// 联系人
	private String remark;		// 备注

	private String carNum;		// 车牌号
	private String joinNum;		//参加数量
	
	public TbTrip() {
		super();
	}

	public TbTrip(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Length(min=0, max=40, message="车辆ID长度必须介于 0 和 40 之间")
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTripTime() {
		return tripTime;
	}

	public void setTripTime(Date tripTime) {
		this.tripTime = tripTime;
	}
	
	@Length(min=0, max=200, message="接送地点长度必须介于 0 和 200 之间")
	public String getTripAddress() {
		return tripAddress;
	}

	public void setTripAddress(String tripAddress) {
		this.tripAddress = tripAddress;
	}
	
	@Length(min=0, max=200, message="联系人长度必须介于 0 和 200 之间")
	public String getCallMan() {
		return callMan;
	}

	public void setCallMan(String callMan) {
		this.callMan = callMan;
	}
	
	@Length(min=0, max=300, message="备注长度必须介于 0 和 300 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Length(min=0, max=20, message="车牌号长度必须介于 0 和 20 之间")
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(String joinNum) {
		this.joinNum = joinNum;
	}
}