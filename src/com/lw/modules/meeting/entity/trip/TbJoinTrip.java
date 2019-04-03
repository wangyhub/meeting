/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.trip;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 用户行程Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbJoinTrip extends DataEntity<TbJoinTrip> {
	
	private static final long serialVersionUID = 1L;
	private String tripId;		// 行程ID
	private String joinId;		// 报名ID

	//add
	private String userName;
	private String companyName;
	private String position;
	
	public TbJoinTrip() {
		super();
	}

	public TbJoinTrip(String id){
		super(id);
	}

	@Length(min=0, max=40, message="行程ID长度必须介于 0 和 40 之间")
	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	
	@Length(min=0, max=40, message="报名ID长度必须介于 0 和 40 之间")
	public String getJoinId() {
		return joinId;
	}

	public void setJoinId(String joinId) {
		this.joinId = joinId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}