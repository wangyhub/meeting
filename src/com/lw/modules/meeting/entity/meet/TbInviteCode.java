/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.lw.common.persistence.DataEntity;

/**
 * 邀请码生成Entity
 * @author meijx
 * @version 2019-03-07
 */
public class TbInviteCode extends DataEntity<TbInviteCode> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String inviteCode;		// 会议邀请码
	private String status;		// 状态
	private String operator;		// 使用人
	private Date operateTime;		// 使用时间
	
	public TbInviteCode() {
		super();
	}

	public TbInviteCode(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=20, message="会议邀请码长度必须介于 0 和 20 之间")
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=20, message="使用人长度必须介于 0 和 20 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
}