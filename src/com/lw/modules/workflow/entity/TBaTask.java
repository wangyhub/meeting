/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;
import com.lw.modules.base.entity.TBaFile;

/**
 * 任务记录Entity
 * @author 张旭东
 * @version 2015-09-01
 */
public class TBaTask extends DataEntity<TBaTask> {
	
	private static final long serialVersionUID = 1L;
	private String noteId;		   // 节点ID
	private String activityId;	   // 流程ID
	private String noteName;	   // 节点名称
	private String dualOpinion;    // 处理意见  
	private String opinionContent; // 意见说明
	private String updateName;	   // 处理人姓名
	private String createName;	   // 上一步处理人姓名
	private String attIds;		   // 附件ID
	private String status;         // 节点状态
	private String nextDualRole;   // 处理岗位
	private String nextDualPerson; // 下一处理人
	private String isSend;         // 是否发送短信
	private String sendContent;    // 短信内容
	private String dualType;//处理类型
	private String lastTaskId;//上一步任务任务ID
	private String roleId;//角色
	private String standby;//备用字段
	private String standby1;//备用字段
    private List<TBaFile> tBaFileList;
    private String days;//超期天数  注：必须记录下来，不能实时去查，否则节点变动时，时间不对。
	
	public TBaTask() {
		super();
	}

	public TBaTask(String id){
		super(id);
	}

	@Length(min=0, max=40, message="节点ID长度必须介于 0 和 40 之间")
	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	
	@Length(min=0, max=40, message="流程ID长度必须介于 0 和 40 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=40, message="节点名称长度必须介于 0 和 40 之间")
	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	@Length(min=0, max=2, message="处理意见长度必须介于 0 和 2 之间")
	public String getDualOpinion() {
		return dualOpinion;
	}

	public void setDualOpinion(String dualOpinion) {
		this.dualOpinion = dualOpinion;
	}
	
	@Length(min=0, max=255, message="意见说明长度必须介于 0 和 255 之间")
	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
	}
	
	@Length(min=0, max=200, message="处理人姓名长度必须介于 0 和 200 之间")
	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	@Length(min=0, max=200, message="上一步处理人姓名长度必须介于 0 和 200 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	@Length(min=0, max=200, message="附件ID长度必须介于 0 和 200 之间")
	public String getAttIds() {
		return attIds;
	}

	public void setAttIds(String attIds) {
		this.attIds = attIds;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getNextDualPerson() {
        return nextDualPerson;
    }

    public void setNextDualPerson(String nextDualPerson) {
        this.nextDualPerson = nextDualPerson;
    }
    
    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

	public String getNextDualRole() {
		return nextDualRole;
	}

	public void setNextDualRole(String nextDualRole) {
		this.nextDualRole = nextDualRole;
	}

    public String getDualType() {
        return dualType;
    }

    public void setDualType(String dualType) {
        this.dualType = dualType;
    }

	public String getLastTaskId() {
		return lastTaskId;
	}

	public void setLastTaskId(String lastTaskId) {
		this.lastTaskId = lastTaskId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getStandby() {
		return standby;
	}

	public void setStandby(String standby) {
		this.standby = standby;
	}
	

    public List<TBaFile> getTBaFileList() {
        return tBaFileList;
    }

    public void setTBaFileList(List<TBaFile> tBaFileList) {
        this.tBaFileList = tBaFileList;
    }

	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}