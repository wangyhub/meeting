/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.back;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 数据维护Entity
 * @author meijx
 * @version 2019-03-21
 */
public class TbBackstage extends DataEntity<TbBackstage> {
	
	private static final long serialVersionUID = 1L;
	private String execType;		// 类型
	private String execSql;		// sql语句
	private String execTable;		// 对象表名
	private Long affectNum;		// 影响数

	private String userName;
	
	public TbBackstage() {
		super();
	}

	public TbBackstage(String id){
		super(id);
	}

	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getExecType() {
		return execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}
	
	@Length(min=0, max=500, message="sql语句长度必须介于 0 和 500 之间")
	public String getExecSql() {
		return execSql;
	}

	public void setExecSql(String execSql) {
		this.execSql = execSql;
	}
	
	@Length(min=0, max=50, message="对象表名长度必须介于 0 和 50 之间")
	public String getExecTable() {
		return execTable;
	}

	public void setExecTable(String execTable) {
		this.execTable = execTable;
	}
	
	public Long getAffectNum() {
		return affectNum;
	}

	public void setAffectNum(Long affectNum) {
		this.affectNum = affectNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}