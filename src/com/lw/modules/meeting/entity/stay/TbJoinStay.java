/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.stay;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 用户住宿Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbJoinStay extends DataEntity<TbJoinStay> {
	
	private static final long serialVersionUID = 1L;
	private String stayId;		// 住宿ID
	private String joinId;		// 报名ID

	//add
	private String userName;
	private String companyName;
	private String position;
	
	public TbJoinStay() {
		super();
	}

	public TbJoinStay(String id){
		super(id);
	}

	@Length(min=0, max=40, message="住宿ID长度必须介于 0 和 40 之间")
	public String getStayId() {
		return stayId;
	}

	public void setStayId(String stayId) {
		this.stayId = stayId;
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