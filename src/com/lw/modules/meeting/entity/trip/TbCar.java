/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.trip;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 车辆信息Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbCar extends DataEntity<TbCar> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String carModel;		// 车型
	private Long seatNum;		// 座位数
	private String carNum;		// 车牌号
	private String carMan;		// 联系人
	private String manPhone;		// 联系人电话
	
	public TbCar() {
		super();
	}

	public TbCar(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=50, message="车型长度必须介于 0 和 50 之间")
	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	
	public Long getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Long seatNum) {
		this.seatNum = seatNum;
	}
	
	@Length(min=0, max=20, message="车牌号长度必须介于 0 和 20 之间")
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	
	@Length(min=0, max=20, message="联系人长度必须介于 0 和 20 之间")
	public String getCarMan() {
		return carMan;
	}

	public void setCarMan(String carMan) {
		this.carMan = carMan;
	}
	
	@Length(min=0, max=20, message="联系人电话长度必须介于 0 和 20 之间")
	public String getManPhone() {
		return manPhone;
	}

	public void setManPhone(String manPhone) {
		this.manPhone = manPhone;
	}
	
}