/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.user;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.dao.user.TbUserDao;
import com.lw.modules.meeting.entity.model.*;
import com.lw.modules.meeting.entity.user.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 参会用户Service
 * @author meijx
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly = true)
public class TbUserService extends CrudService<TbUserDao, TbUser> {
	@Autowired
	private TbUserDao tbUserDao;

	public TbUser get(String id) {
		return super.get(id);
	}
	
	public List<TbUser> findList(TbUser tbUser) {
		return super.findList(tbUser);
	}
	
	public Page<TbUser> findPage(Page<TbUser> page, TbUser tbUser) {
		return super.findPage(page, tbUser);
	}
	
	@Transactional(readOnly = false)
	public void save(TbUser tbUser) {
		super.save(tbUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbUser tbUser) {
		super.delete(tbUser);
	}

	public AppResult findByPhone(String phone){
		LoginUser loginUser = tbUserDao.findByPhone(phone);
		if(loginUser == null){
			//没有用户就添加用户
			TbUser tbUser = new TbUser();
			tbUser.setPhone(phone);
			this.save(tbUser);
			loginUser = tbUserDao.findByPhone(phone);
		}
		return AppResult.ok(loginUser);
	}

	public TbSeatContent findById(String userId) {

		TbSeatContent tbSeatContent =	tbUserDao.findById(userId);

		return  tbSeatContent;

	}

	public AppResult getUserById(String userId,String meetingId){
		JoinUser user = tbUserDao.getUserById(userId);
		user.setMeetingId(meetingId);
		if(user != null){
			return AppResult.ok(user);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.LOGIN_ERROR_MSG, null);
		}
	}

	public TbSelfContent findMyData(String userId,String meetingId) {
		TbSelfContent tbSelfContent = tbUserDao.findMyData(userId,meetingId);

		return  tbSelfContent;
	}
}