/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.lw.common.persistence.DataEntity;

/**
 * 日程安排Entity
 * @author meijx
 * @version 2019-03-11
 */
public class TbMeetingAgenda extends DataEntity<TbMeetingAgenda> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String placeId;		// 会场ID
	private String name;		//名称
	private String subject;		// 主题
	private Date beginDate;		// 开始时间
	private Date endDate;		// 结束时间
	private String moderator;		// 主持人
	private List<TbAgendaDetail> tbAgendaDetailList = Lists.newArrayList();		// 子表列表

	//add
	private String placeName;		// 会场名称
	
	public TbMeetingAgenda() {
		super();
	}

	public TbMeetingAgenda(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=40, message="会场ID长度必须介于 0 和 40 之间")
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	@Length(min=0, max=200, message="主题长度必须介于 0 和 200 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=20, message="主持人长度必须介于 0 和 20 之间")
	public String getModerator() {
		return moderator;
	}

	public void setModerator(String moderator) {
		this.moderator = moderator;
	}
	
	public List<TbAgendaDetail> getTbAgendaDetailList() {
		return tbAgendaDetailList;
	}

	public void setTbAgendaDetailList(List<TbAgendaDetail> tbAgendaDetailList) {
		this.tbAgendaDetailList = tbAgendaDetailList;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}