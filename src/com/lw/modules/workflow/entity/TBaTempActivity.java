/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.lw.common.persistence.DataEntity;
import com.lw.modules.sys.entity.Office;

/**
 * 流程管理Entity
 * @author handf
 * @version 2015-09-01
 */
public class TBaTempActivity extends DataEntity<TBaTempActivity> {
	
	private static final long serialVersionUID = 1L;
	private String activityName; // 流程定义名称
	private String logo;		 // 流程标识
	private String status;		 // 状态:1运行中2终止3暂停
	private Office company;      // 公司
	private List<TBaTempNote> tBaTempNoteList = Lists.newArrayList();		// 子表列表
	
	public TBaTempActivity() {
		super();
	}

	public TBaTempActivity(String id){
		super(id);
	}	
	
	@Length(min=0, max=40, message="流程标识长度必须介于 0 和 40 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=1, message="状态:1运行中2终止3暂停长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<TBaTempNote> getTBaTempNoteList() {
		return tBaTempNoteList;
	}

	public void setTBaTempNoteList(List<TBaTempNote> tBaTempNoteList) {
		this.tBaTempNoteList = tBaTempNoteList;
	}

	@Length(min=0, max=40, message="流程标识长度必须介于 0 和 200 之间")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Office getCompany() {
        return company;
    }

    public void setCompany(Office company) {
        this.company = company;
    }

}