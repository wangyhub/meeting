/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.lw.common.persistence.DataEntity;

/**
 * 表单配置Entity
 * @author weixm
 * @version 2016-03-29
 */
public class TBaTableConfig extends DataEntity<TBaTableConfig> {
	
	private static final long serialVersionUID = 1L;
	private String tableName;		// 表名
	private String content;		// 表描述
	private String type;		// 表类型01单表 02 主表 03 附表
	private String relationType;		// 表对应关系:01一对多02一对一
	private String isPagination;		// 是否分页：01是 02 否
	private String matchDataBase;		//是否同步数据库：00否01是
	private List<TBaTableField> tBaTableFieldList = Lists.newArrayList();//查询数据库存储
	private String menuId;				//菜单id
	private String menuParentId;		//菜单父id
	
	public TBaTableConfig() {
		super();
	}

	public TBaTableConfig(String id){
		super(id);
	}

	@Length(min=0, max=50, message="表名长度必须介于 0 和 50 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Length(min=0, max=200, message="表描述长度必须介于 0 和 200 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2, message="表类型01单表 02 主表 03 附表长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2, message="表对应关系:01一对多02一对一长度必须介于 0 和 2 之间")
	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	@Length(min=0, max=2, message="是否分页：01是 02 否长度必须介于 0 和 2 之间")
	public String getIsPagination() {
		return isPagination;
	}

	public void setIsPagination(String isPagination) {
		this.isPagination = isPagination;
	}
	
	public String getMatchDataBase() {
		return matchDataBase;
	}

	public void setMatchDataBase(String matchDataBase) {
		this.matchDataBase = matchDataBase;
	}

	public List<TBaTableField> getTBaTableFieldList() {
		return tBaTableFieldList;
	}

	public void setTBaTableFieldList(List<TBaTableField> tBaTableFieldList) {
		this.tBaTableFieldList = tBaTableFieldList;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}
	


	
	
}