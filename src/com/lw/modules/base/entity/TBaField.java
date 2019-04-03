/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.entity;

import com.lw.common.persistence.DataEntity;

/**
 * 动态查询Entity
 * @author handf
 * @version 2016-03-24
 */
public class TBaField extends DataEntity<TBaField> {
	
	private static final long serialVersionUID = 1L;
	private String codeName;		// 编码名称
	private String fieldSort;		// 字段排序
	private String fieldInput;		// 字段文本框
	private String fieldType;		// 字段类型
	private String physicsType;		// 物理类型
	private String physicsLength;   // 物理字段长度
	private String isShow;		// 字段是否显示
	private String fieldHref;		// 字段超链接
	private String selectModel;		// 查询模式
	private String selectType;		// 查询类型
	private String dictCode;		// 字典编码
	private String isSelect;		// 是否查询
	private TBaMainSql main;		// 主表id 父类
	
	public TBaField() {
		super();
	}

	public TBaField(String id){
		super(id);
	}

	public TBaField(TBaMainSql main){
		this.main = main;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public String getFieldSort() {
		return fieldSort;
	}

	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}
	
	public String getFieldInput() {
		return fieldInput;
	}

	public void setFieldInput(String fieldInput) {
		this.fieldInput = fieldInput;
	}
	
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public String getPhysicsType() {
		return physicsType;
	}

	public void setPhysicsType(String physicsType) {
		this.physicsType = physicsType;
	}
	
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	public String getFieldHref() {
		return fieldHref;
	}

	public void setFieldHref(String fieldHref) {
		this.fieldHref = fieldHref;
	}
	
	public String getSelectModel() {
		return selectModel;
	}

	public void setSelectModel(String selectModel) {
		this.selectModel = selectModel;
	}
	
	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	
	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	
	public TBaMainSql getMain() {
		return main;
	}

	public void setMain(TBaMainSql main) {
		this.main = main;
	}

	public String getPhysicsLength() {
		return physicsLength;
	}

	public void setPhysicsLength(String physicsLength) {
		this.physicsLength = physicsLength;
	}

}