/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 会场管理Entity
 * @author meijx
 * @version 2019-03-09
 */
public class TbMeetingPlace extends DataEntity<TbMeetingPlace> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String placeName;		// 会场名称
	private String placeTel;		// 联系电话
	private String placeAddress;		// 会场地址
	private String placeRoute;		// 路线提示
	private String pointX;		// X坐标
	private String pointY;		// Y坐标
	private Long contain;		// 容纳人数
	private Long platform;		// 主席台
	private String region;		// 参会席
	private Long regionRow;		// 参会席行
	private String status;		// 状态
	
	public TbMeetingPlace() {
		super();
	}

	public TbMeetingPlace(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=50, message="会场名称长度必须介于 0 和 50 之间")
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getPlaceTel() {
		return placeTel;
	}

	public void setPlaceTel(String placeTel) {
		this.placeTel = placeTel;
	}
	
	@Length(min=0, max=100, message="会场地址长度必须介于 0 和 100 之间")
	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}
	
	@Length(min=0, max=200, message="路线提示长度必须介于 0 和 200 之间")
	public String getPlaceRoute() {
		return placeRoute;
	}

	public void setPlaceRoute(String placeRoute) {
		this.placeRoute = placeRoute;
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
	
	public Long getContain() {
		return contain;
	}

	public void setContain(Long contain) {
		this.contain = contain;
	}
	
	public Long getPlatform() {
		return platform;
	}

	public void setPlatform(Long platform) {
		this.platform = platform;
	}
	
	@Length(min=0, max=1000, message="参会席长度必须介于 0 和 1000 之间")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public Long getRegionRow() {
		return regionRow;
	}

	public void setRegionRow(Long regionRow) {
		this.regionRow = regionRow;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}