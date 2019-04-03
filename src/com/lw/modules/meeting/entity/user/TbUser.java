/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.user;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 参会用户Entity
 * @author meijx
 * @version 2019-03-08
 */
public class TbUser extends DataEntity<TbUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 姓名
	private String sex;		// 性别
	private String nation;		// 名族
	private String phone;		// 手机
	private String email;		// 邮箱
	
	public TbUser() {
		super();
	}

	public TbUser(String id){
		super(id);
	}

	@Length(min=0, max=20, message="姓名长度必须介于 0 和 20 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=20, message="性别长度必须介于 0 和 20 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=20, message="名族长度必须介于 0 和 20 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=20, message="手机长度必须介于 0 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=50, message="邮箱长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}