/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 会议管理Entity
 * @author meijx
 * @version 2019-03-06
 */
public class TbMeetingCompany extends DataEntity<TbMeetingCompany> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String companyName;		// 单位名称
	
	public TbMeetingCompany() {
		super();
	}

	public TbMeetingCompany(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=100, message="单位名称长度必须介于 0 和 100 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}