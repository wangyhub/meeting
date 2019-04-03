/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 表单配置Entity
 * @author weixm
 * @version 2016-03-29
 */
public class TBaTableField extends DataEntity<TBaTableField> {
	
	private static final long serialVersionUID = 1L;
	private String fieldCode;		// 字段编码
	private String fieldName;		// 字段名称
	private String fieldLength;		// 字段长度
	private String fieldType;		// 字段类型
	private String isNull;		// 允许空值
	private String isShowForm;		// 表单显示
	private String isShowList;		// 列表显示
	private String controlType;		// 控件类型
	private String controlLength;		// 控件长度
	private String isQueryCondition;		// 是否查询条件
	private String queryType;		// 查询类型
	private String fieldHref;		// 字段href
	private String fieldValidRule;		// 校验规则
	private String dictTable;		// 字典table
	private String dictCode;		// 字典code
	private String dictText;		// 字典text
	private String fartherTable;		// 主表名
	private String fartherField;		// 主表字段
	private String isDatebaseField;		// 是否数据库字段
	private String listSql;		// 列表自定义sql
	private TBaTableConfig tableId;		// 主表id 父类
	private String oldFieldCode;		//旧字段名称
	private String sort;				//字段排序
	
	public TBaTableField() {
		super();
	}

	public TBaTableField(String id){
		super(id);
	}

	public TBaTableField(TBaTableConfig tableId){
		this.tableId = tableId;
	}

	@Length(min=0, max=100, message="字段编码长度必须介于 0 和 100 之间")
	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	
	@Length(min=0, max=200, message="字段名称长度必须介于 0 和 200 之间")
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Length(min=0, max=200, message="字段长度长度必须介于 0 和 200 之间")
	public String getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	
	@Length(min=0, max=2, message="字段类型长度必须介于 0 和 2 之间")
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	@Length(min=0, max=2, message="允许空值长度必须介于 0 和 2 之间")
	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	
	@Length(min=0, max=2, message="表单显示长度必须介于 0 和 2 之间")
	public String getIsShowForm() {
		return isShowForm;
	}

	public void setIsShowForm(String isShowForm) {
		this.isShowForm = isShowForm;
	}
	
	@Length(min=0, max=2, message="列表显示长度必须介于 0 和 2 之间")
	public String getIsShowList() {
		return isShowList;
	}

	public void setIsShowList(String isShowList) {
		this.isShowList = isShowList;
	}
	
	@Length(min=0, max=2, message="控件类型长度必须介于 0 和 2 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	
	@Length(min=0, max=100, message="控件长度长度必须介于 0 和 100 之间")
	public String getControlLength() {
		return controlLength;
	}

	public void setControlLength(String controlLength) {
		this.controlLength = controlLength;
	}
	
	@Length(min=0, max=2, message="是否查询条件长度必须介于 0 和 2 之间")
	public String getIsQueryCondition() {
		return isQueryCondition;
	}

	public void setIsQueryCondition(String isQueryCondition) {
		this.isQueryCondition = isQueryCondition;
	}
	
	@Length(min=0, max=2, message="查询类型长度必须介于 0 和 2 之间")
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	@Length(min=0, max=200, message="字段href长度必须介于 0 和 200 之间")
	public String getFieldHref() {
		return fieldHref;
	}

	public void setFieldHref(String fieldHref) {
		this.fieldHref = fieldHref;
	}
	
	@Length(min=0, max=200, message="校验规则长度必须介于 0 和 200 之间")
	public String getFieldValidRule() {
		return fieldValidRule;
	}

	public void setFieldValidRule(String fieldValidRule) {
		this.fieldValidRule = fieldValidRule;
	}
	
	@Length(min=0, max=100, message="字典table长度必须介于 0 和 100 之间")
	public String getDictTable() {
		return dictTable;
	}

	public void setDictTable(String dictTable) {
		this.dictTable = dictTable;
	}
	
	@Length(min=0, max=100, message="字典code长度必须介于 0 和 100 之间")
	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	@Length(min=0, max=100, message="字典text长度必须介于 0 和 100 之间")
	public String getDictText() {
		return dictText;
	}

	public void setDictText(String dictText) {
		this.dictText = dictText;
	}
	
	@Length(min=0, max=100, message="主表名长度必须介于 0 和 100 之间")
	public String getFartherTable() {
		return fartherTable;
	}

	public void setFartherTable(String fartherTable) {
		this.fartherTable = fartherTable;
	}
	
	@Length(min=0, max=100, message="主表字段长度必须介于 0 和 100 之间")
	public String getFartherField() {
		return fartherField;
	}

	public void setFartherField(String fartherField) {
		this.fartherField = fartherField;
	}
	
	@Length(min=0, max=2, message="是否数据库字段长度必须介于 0 和 2 之间")
	public String getIsDatebaseField() {
		return isDatebaseField;
	}

	public void setIsDatebaseField(String isDatebaseField) {
		this.isDatebaseField = isDatebaseField;
	}
	
	@Length(min=0, max=4000, message="列表自定义sql长度必须介于 0 和 4000 之间")
	public String getListSql() {
		return listSql;
	}

	public void setListSql(String listSql) {
		this.listSql = listSql;
	}
	
	@Length(min=0, max=40, message="主表id长度必须介于 0 和 40 之间")
	public TBaTableConfig getTableId() {
		return tableId;
	}

	public void setTableId(TBaTableConfig tableId) {
		this.tableId = tableId;
	}

	public String getOldFieldCode() {
		return oldFieldCode;
	}

	public void setOldFieldCode(String oldFieldCode) {
		this.oldFieldCode = oldFieldCode;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String compareField(){
		StringBuilder builder = new StringBuilder();
		builder.append("fieldCode" + getFieldCode());
		builder.append("fieldLength" + getFieldLength());
		builder.append("fieldType" + getFieldType());
		builder.append("isNull" + getIsNull());
		return builder.toString();
	}

}