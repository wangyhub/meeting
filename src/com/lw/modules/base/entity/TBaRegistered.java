/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 注册模块Entity
 * @author handf
 * @version 2015-08-13
 */
public class TBaRegistered extends DataEntity<TBaRegistered> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 机构名称
	private String orgCode;		// 组织机构代码
	private String userId;		// 系统用户ID
	private String phone;		// 短信校验号码
	
	public TBaRegistered() {
		super();
	}

	public TBaRegistered(String id){
		super(id);
	}

	@Length(min=0, max=200, message="机构名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="组织机构代码长度必须介于 0 和 50 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Length(min=0, max=50, message="短信校验号码长度必须介于 0 和 50 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}