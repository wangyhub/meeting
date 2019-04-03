/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.demo.entity;

import com.lw.common.persistence.DataEntity;

/**
 * Excel演示Entity
 * @author handf
 * @version 2016-08-26
 */
public class TBzExpertExcelDemo extends DataEntity<TBzExpertExcelDemo> {
	
	private static final long serialVersionUID = 1L;
	private String columnOne;		// 第一列
	private String columnTwo;		// 第二列
	private String columnThree;		// 第三列
	private String sort; //排序
	
	public TBzExpertExcelDemo() {
		super();
	}

	public TBzExpertExcelDemo(String id){
		super(id);
	}

	public String getColumnOne() {
		return columnOne;
	}

	public void setColumnOne(String columnOne) {
		this.columnOne = columnOne;
	}
	
	public String getColumnTwo() {
		return columnTwo;
	}

	public void setColumnTwo(String columnTwo) {
		this.columnTwo = columnTwo;
	}
	
	public String getColumnThree() {
		return columnThree;
	}

	public void setColumnThree(String columnThree) {
		this.columnThree = columnThree;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}