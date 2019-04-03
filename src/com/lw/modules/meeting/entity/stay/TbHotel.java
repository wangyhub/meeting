/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.stay;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 酒店信息Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbHotel extends DataEntity<TbHotel> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String hotelName;		// 酒店名称
	private String hotelAddress;		// 酒店地址
	private String hotelPhone;		// 酒店电话
	private String stayNotice;		// 入住须知
	private String pointX;		// X坐标
	private String pointY;		// Y坐标
	
	public TbHotel() {
		super();
	}

	public TbHotel(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=100, message="酒店名称长度必须介于 0 和 100 之间")
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	@Length(min=0, max=200, message="酒店地址长度必须介于 0 和 200 之间")
	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	
	@Length(min=0, max=20, message="酒店电话长度必须介于 0 和 20 之间")
	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}
	
	@Length(min=0, max=200, message="入住须知长度必须介于 0 和 200 之间")
	public String getStayNotice() {
		return stayNotice;
	}

	public void setStayNotice(String stayNotice) {
		this.stayNotice = stayNotice;
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