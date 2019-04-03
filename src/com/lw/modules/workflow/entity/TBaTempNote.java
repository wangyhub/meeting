/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 流程管理Entity
 * @author handf
 * @version 2015-09-01
 */
public class TBaTempNote extends DataEntity<TBaTempNote> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 流程节点名称
	private TBaTempActivity activityId;		// 流程实例模板ID 父类
	private String status;		// 状态:1运行中2终止3暂停
	private String sendSms;		// 发送短信:1发送2不发送
	private String smsContent;		// 发送短信内容
	private Long priority;		// 优先级
	private List<TBaNoteRole> roleList; // 选择的角色
	private List<String> roleIdList; // 角色Id的信息集合
	
	public TBaTempNote() {
		super();
	}

	public TBaTempNote(String id){
		super(id);
	}

	public TBaTempNote(TBaTempActivity activityId){
		this.activityId = activityId;
	}

	@Length(min=0, max=40, message="流程节点名称长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=40, message="流程实例模板ID长度必须介于 0 和 40 之间")
	public TBaTempActivity getActivityId() {
		return activityId;
	}

	public void setActivityId(TBaTempActivity activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=1, message="状态:1运行中2终止3暂停长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="发送短信:1发送2不发送长度必须介于 0 和 1 之间")
	public String getSendSms() {
		return sendSms;
	}

	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	
	@Length(min=0, max=4000, message="发送短信内容长度必须介于 0 和 4000 之间")
	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	
	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
    public List<TBaNoteRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TBaNoteRole> roleList) {
        this.roleList = roleList;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }
	
}