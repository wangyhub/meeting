/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 流程实例Entity
 * @author zhangxudong
 * @version 2015-09-01
 */
public class TBaNote extends DataEntity<TBaNote> {
	
	private static final long serialVersionUID = 1L;
	private String tempNoteId;		// 流程节点模板ID
	private TBaActivity activityId;		// 流程实例ID 父类
	private String name;		// 节点名称
	private String status;		// 节点状态
	
	public TBaNote() {
		super();
	}

	public TBaNote(String id){
		super(id);
	}

	public TBaNote(TBaActivity activityId){
		this.activityId = activityId;
	}

	@Length(min=0, max=40, message="流程节点模板ID长度必须介于 0 和 40 之间")
	public String getTempNoteId() {
		return tempNoteId;
	}

	public void setTempNoteId(String tempNoteId) {
		this.tempNoteId = tempNoteId;
	}
	
	@Length(min=0, max=40, message="流程实例ID长度必须介于 0 和 40 之间")
	public TBaActivity getActivityId() {
		return activityId;
	}

	public void setActivityId(TBaActivity activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=40, message="节点名称长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="节点状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}