/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lw.common.persistence.DataEntity;
import com.lw.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 消息记录信息Entity
 * @author wangyi
 * @version 2019-03-14
 */
public class TbMessageRecord extends DataEntity<TbMessageRecord> {
	
	private static final long serialVersionUID = 1L;
	private String messageId;		// 消息ID
	private String userPhone;		// 用户手机
	private Date messageTime;		// 发送时间
	private String status;		// 状态
	private String userId;		// 用户ID
	private String userName;	//用户名
	
	public TbMessageRecord() {
		super();
	}

	public TbMessageRecord(String id){
		super(id);
	}

	@Length(min=0, max=40, message="消息ID长度必须介于 0 和 40 之间")
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	@Length(min=0, max=50, message="用户手机长度必须介于 0 和 50 之间")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}