/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meal;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 用户用餐Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbJoinMeal extends DataEntity<TbJoinMeal> {
	
	private static final long serialVersionUID = 1L;
	private String mealId;		// 用餐ID
	private String joinId;		// 报名ID
	private String tableNum;		// 桌号

	//add
	private String userName;
	private String companyName;
	private String position;
	
	public TbJoinMeal() {
		super();
	}

	public TbJoinMeal(String id){
		super(id);
	}

	@Length(min=0, max=40, message="用餐ID长度必须介于 0 和 40 之间")
	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	
	@Length(min=0, max=40, message="报名ID长度必须介于 0 和 40 之间")
	public String getJoinId() {
		return joinId;
	}

	public void setJoinId(String joinId) {
		this.joinId = joinId;
	}
	
	@Length(min=0, max=10, message="桌号长度必须介于 0 和 10 之间")
	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}