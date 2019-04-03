/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import java.util.List;

import com.lw.common.persistence.DataEntity;

/**
 * 动态查询Entity
 * @author handf
 * @version 2016-03-14
 */
public class TBaMainSql extends DataEntity<TBaMainSql> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 生成页面的title
	private String headSql;		// 需拼接的sql头部分
	private String footSql;		// 需拼接的sql尾部分
	private String sqlFlag;		// 建立的动态查询的唯一标志符
	private String sqlShow;		// 最终拼接成功后的sql
	private String formFlag;//页面标志"main":主页面;"split":条件页面;"show":展现字段页面
	private List<TBaField> fieldList;//字段属性
	
	public TBaMainSql() {
		super();
	}

	public TBaMainSql(String id){
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHeadSql() {
		return headSql;
	}

	public void setHeadSql(String headSql) {
		this.headSql = headSql;
	}
	
	public String getFootSql() {
		return footSql;
	}

	public void setFootSql(String footSql) {
		this.footSql = footSql;
	}

	public String getSqlFlag() {
		return sqlFlag;
	}

	public void setSqlFlag(String sqlFlag) {
		this.sqlFlag = sqlFlag;
	}
	
	public String getSqlShow() {
		return sqlShow;
	}

	public void setSqlShow(String sqlShow) {
		this.sqlShow = sqlShow;
	}
	
	public String getFormFlag() {
		return formFlag;
	}

	public void setFormFlag(String formFlag) {
		this.formFlag = formFlag;
	}

	public List<TBaField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<TBaField> fieldList) {
		this.fieldList = fieldList;
	}

}