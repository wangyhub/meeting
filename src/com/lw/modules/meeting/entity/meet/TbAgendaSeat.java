/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 座次安排Entity
 * @author meijx
 * @version 2019-03-12
 */
public class TbAgendaSeat extends DataEntity<TbAgendaSeat> {
	
	private static final long serialVersionUID = 1L;
	private String agendaId;		// 议程ID
	private String joinId;		// 报名ID
	private String seatNo;		// 座位号
	
	public TbAgendaSeat() {
		super();
	}

	public TbAgendaSeat(String id){
		super(id);
	}

	@Length(min=0, max=40, message="议程ID长度必须介于 0 和 40 之间")
	public String getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(String agendaId) {
		this.agendaId = agendaId;
	}
	
	@Length(min=0, max=40, message="报名ID长度必须介于 0 和 40 之间")
	public String getJoinId() {
		return joinId;
	}

	public void setJoinId(String joinId) {
		this.joinId = joinId;
	}
	
	@Length(min=0, max=10, message="座位号长度必须介于 0 和 10 之间")
	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	
}