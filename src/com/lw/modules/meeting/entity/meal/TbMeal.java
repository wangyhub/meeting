/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meal;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.lw.common.persistence.DataEntity;

/**
 * 用餐信息Entity
 * @author meijx
 * @version 2019-03-13
 */
public class TbMeal extends DataEntity<TbMeal> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		// 会议ID
	private String mealName;		// 餐厅名称
	private String mealAddress;		// 餐厅地址
	private String mealPhone;		// 联系电话
	private Date mealDate;		// 用餐时间
	private String mealType;		// 用餐类型
	private String mealKind;		// 用餐种类
	private String isTable;		// 是否分桌

	private String joinNum;		//参加数量
	
	public TbMeal() {
		super();
	}

	public TbMeal(String id){
		super(id);
	}

	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=100, message="餐厅名称长度必须介于 0 和 100 之间")
	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	
	@Length(min=0, max=200, message="餐厅地址长度必须介于 0 和 200 之间")
	public String getMealAddress() {
		return mealAddress;
	}

	public void setMealAddress(String mealAddress) {
		this.mealAddress = mealAddress;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getMealPhone() {
		return mealPhone;
	}

	public void setMealPhone(String mealPhone) {
		this.mealPhone = mealPhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMealDate() {
		return mealDate;
	}

	public void setMealDate(Date mealDate) {
		this.mealDate = mealDate;
	}
	
	@Length(min=0, max=20, message="用餐类型长度必须介于 0 和 20 之间")
	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	
	@Length(min=0, max=20, message="用餐种类长度必须介于 0 和 20 之间")
	public String getMealKind() {
		return mealKind;
	}

	public void setMealKind(String mealKind) {
		this.mealKind = mealKind;
	}
	
	@Length(min=0, max=2, message="是否分桌长度必须介于 0 和 2 之间")
	public String getIsTable() {
		return isTable;
	}

	public void setIsTable(String isTable) {
		this.isTable = isTable;
	}

	public String getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(String joinNum) {
		this.joinNum = joinNum;
	}
}