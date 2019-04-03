/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 会议创建码Entity
 * @author meijx
 * @version 2019-03-25
 */
public class TbCreateCode extends DataEntity<TbCreateCode> {
	
	private static final long serialVersionUID = 1L;
	private String createCode;		// 会议创建码
	private String status;		// 状态
	
	public TbCreateCode() {
		super();
	}

	public TbCreateCode(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议创建码长度必须介于 0 和 40 之间")
	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}