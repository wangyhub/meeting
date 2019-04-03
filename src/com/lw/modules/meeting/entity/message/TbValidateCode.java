/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.message;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.lw.common.persistence.DataEntity;

/**
 * 短信验证码Entity
 * @author meijx
 * @version 2019-03-19
 */
public class TbValidateCode extends DataEntity<TbValidateCode> {
	
	private static final long serialVersionUID = 1L;
	private String userPhone;		// 用户手机
	private Date messageTime;		// 发送时间
	private String status;		// 状态
	private String validateCode;		// 验证码
	
	public TbValidateCode() {
		super();
	}

	public TbValidateCode(String id){
		super(id);
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
	
	@Length(min=0, max=20, message="验证码长度必须介于 0 和 20 之间")
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}