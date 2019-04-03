/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lw.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 消息信息Entity
 * @author wangyi
 * @version 2019-03-14
 */
public class TbMessage extends DataEntity<TbMessage> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String kind;		// 种类
	private String type;		// 类型
	private String content;		// 内容
	private String status;		// 状态
	private Date sendTime;		// 发送时间

	private String sendNum;		//参加数量
	
	public TbMessage() {
		super();
	}

	public TbMessage(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=2, message="种类长度必须介于 0 和 2 之间")
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendNum() {
		return sendNum;
	}

	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}
}