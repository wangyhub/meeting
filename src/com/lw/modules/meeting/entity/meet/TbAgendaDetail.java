/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 日程安排Entity
 * @author meijx
 * @version 2019-03-11
 */
public class TbAgendaDetail extends DataEntity<TbAgendaDetail> {
	
	private static final long serialVersionUID = 1L;
	private String agendaId;		// 议程ID 父类
	private String agendaTime;		// 时间
	private String subject;		// 主题
	private String content;		// 内容
	
	public TbAgendaDetail() {
		super();
	}

	public TbAgendaDetail(String id){
		super(id);
	}

	@Length(min=0, max=40, message="议程ID长度必须介于 0 和 40 之间")
	public String getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(String agendaId) {
		this.agendaId = agendaId;
	}
	
	@Length(min=0, max=20, message="时间长度必须介于 0 和 20 之间")
	public String getAgendaTime() {
		return agendaTime;
	}

	public void setAgendaTime(String agendaTime) {
		this.agendaTime = agendaTime;
	}
	
	@Length(min=0, max=200, message="主题长度必须介于 0 和 200 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Length(min=0, max=200, message="内容长度必须介于 0 和 200 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}