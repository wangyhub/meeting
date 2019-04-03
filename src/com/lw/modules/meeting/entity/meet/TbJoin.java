/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.entity.meet;

import com.lw.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.lw.common.persistence.DataEntity;

/**
 * 参会人管理Entity
 * @author meijx
 * @version 2019-03-07
 */
public class TbJoin extends DataEntity<TbJoin> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String meetingId;		// 会议ID
	private String joinType;		// 参加类型
	private String companyId;		// 单位
	private String position;		// 职务
	private String inviteCode;		// 邀请码
	private String isComeStation;		// 是否接站
	private String comeNumber;		// 到达航班/车次
	private String comeStation;		// 到达站
	private Date comeTime;		// 到达时间
	private String comeAddress;		// 接站地址
	private String isSendStation;		// 是否送站
	private String goNumber;		// 离开航班/车次
	private String goStation;		// 离开站
	private Date goTime;		// 离开时间
	private String goAddress;		// 送站地址
	private String isLive;		// 是否住宿

	//add
	private String userName;		// 姓名
	private String sex;				// 性别
	private String nation;			// 名族
	private String phone;			// 手机
	private String email;			// 邮箱
	private String companyName;		//单位名称

	private String chooseJoin;
	
	public TbJoin() {
		super();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public TbJoin(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=40, message="会议ID长度必须介于 0 和 40 之间")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	@Length(min=0, max=2, message="参加类型长度必须介于 0 和 2 之间")
	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}
	
	@Length(min=0, max=40, message="单位长度必须介于 0 和 40 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Length(min=0, max=100, message="职务长度必须介于 0 和 100 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=20, message="邀请码长度必须介于 0 和 20 之间")
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	@Length(min=0, max=2, message="是否接站长度必须介于 0 和 2 之间")
	public String getIsComeStation() {
		return isComeStation;
	}

	public void setIsComeStation(String isComeStation) {
		this.isComeStation = isComeStation;
	}
	
	@Length(min=0, max=20, message="到达航班/车次长度必须介于 0 和 20 之间")
	public String getComeNumber() {
		return comeNumber;
	}

	public void setComeNumber(String comeNumber) {
		this.comeNumber = comeNumber;
	}
	
	@Length(min=0, max=20, message="到达站长度必须介于 0 和 20 之间")
	public String getComeStation() {
		return comeStation;
	}

	public void setComeStation(String comeStation) {
		this.comeStation = comeStation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getComeTime() {
		return comeTime;
	}

	public void setComeTime(Date comeTime) {
		this.comeTime = comeTime;
	}
	
	@Length(min=0, max=100, message="接站地址长度必须介于 0 和 100 之间")
	public String getComeAddress() {
		return comeAddress;
	}

	public void setComeAddress(String comeAddress) {
		this.comeAddress = comeAddress;
	}
	
	@Length(min=0, max=2, message="是否送站长度必须介于 0 和 2 之间")
	public String getIsSendStation() {
		return isSendStation;
	}

	public void setIsSendStation(String isSendStation) {
		this.isSendStation = isSendStation;
	}
	
	@Length(min=0, max=20, message="离开航班/车次长度必须介于 0 和 20 之间")
	public String getGoNumber() {
		return goNumber;
	}

	public void setGoNumber(String goNumber) {
		this.goNumber = goNumber;
	}
	
	@Length(min=0, max=20, message="离开站长度必须介于 0 和 20 之间")
	public String getGoStation() {
		return goStation;
	}

	public void setGoStation(String goStation) {
		this.goStation = goStation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGoTime() {
		return goTime;
	}

	public void setGoTime(Date goTime) {
		this.goTime = goTime;
	}
	
	@Length(min=0, max=100, message="送站地址长度必须介于 0 和 100 之间")
	public String getGoAddress() {
		return goAddress;
	}

	public void setGoAddress(String goAddress) {
		this.goAddress = goAddress;
	}
	
	@Length(min=0, max=2, message="是否住宿长度必须介于 0 和 2 之间")
	public String getIsLive() {
		return isLive;
	}

	public void setIsLive(String isLive) {
		this.isLive = isLive;
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

	public String getChooseJoin() {
		return chooseJoin;
	}

	public void setChooseJoin(String chooseJoin) {
		this.chooseJoin = chooseJoin;
	}

}