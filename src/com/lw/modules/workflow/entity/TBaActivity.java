/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.modules.sys.entity.Office;
import com.lw.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.lw.common.persistence.DataEntity;

/**
 * 流程实例Entity
 * @author zhangxudong
 * @version 2015-09-01
 */
public class TBaActivity extends DataEntity<TBaActivity> {
	
	private static final long serialVersionUID = 1L;
	private String tempActivityId; // 流程定义ID
	private String applyCode;	   // 申请编号
	private String applyState;	   // 申请状态
	private String applyId;		   // 申请ID
	private String status;		   // 流程实例状态:1运行中2结束3终止4暂停
	private String reviewAdvice;   // 审批结果
	private Office company;		   // 组织机构ID
	private String orgName;		   // 机构名称
	private User user;		       // 机构ID
	private List<TBaNote> tBaNoteList = Lists.newArrayList();		// 子表列表
	private String activityName;   // 流程定义名称
	private String certCode;//机构批准号
	private String dualDay;//审批过程经历工作日天数

	public TBaActivity() {
		super();
	}

	public TBaActivity(String id){
		super(id);
	}

	@Length(min=0, max=40, message="流程定义ID长度必须介于 0 和 40 之间")
	public String getTempActivityId() {
		return tempActivityId;
	}

	public void setTempActivityId(String tempActivityId) {
		this.tempActivityId = tempActivityId;
	}
	
	@Length(min=0, max=40, message="申请编号长度必须介于 0 和 40 之间")
	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
	
	@Length(min=0, max=2, message="申请状态长度必须介于 0 和 2 之间")
	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	
	@Length(min=0, max=40, message="申请ID长度必须介于 0 和 40 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Length(min=0, max=10, message="流程实例状态:1运行中2结束3终止4暂停长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="审批结果长度必须介于 0 和 2 之间")
	public String getReviewAdvice() {
		return reviewAdvice;
	}

	public void setReviewAdvice(String reviewAdvice) {
		this.reviewAdvice = reviewAdvice;
	}
	
	public Office getCompany() {
        return company;
    }

    public void setCompany(Office company) {
        this.company = company;
    }

    public List<TBaNote> gettBaNoteList() {
        return tBaNoteList;
    }

    public void settBaNoteList(List<TBaNote> tBaNoteList) {
        this.tBaNoteList = tBaNoteList;
    }

    @Length(min=0, max=200, message="机构名称长度必须介于 0 和 200 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<TBaNote> getTBaNoteList() {
		return tBaNoteList;
	}

	public void setTBaNoteList(List<TBaNote> tBaNoteList) {
		this.tBaNoteList = tBaNoteList;
	}

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getDualDay() {
        return dualDay;
    }

    public void setDualDay(String dualDay) {
        this.dualDay = dualDay;
    }

}