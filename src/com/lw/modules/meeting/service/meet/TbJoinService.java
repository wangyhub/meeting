/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.dao.user.TbUserDao;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.JoinDepartment;
import com.lw.modules.meeting.entity.model.JoinInfo;
import com.lw.modules.meeting.entity.model.MeetingCompany;
import com.lw.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.meet.TbJoin;
import com.lw.modules.meeting.dao.meet.TbJoinDao;

/**
 * 参会人管理Service
 * @author meijx
 * @version 2019-03-07
 */
@Service
@Transactional(readOnly = true)
public class TbJoinService extends CrudService<TbJoinDao, TbJoin> {

	@Autowired
	private TbJoinDao tbJoinDao;
	@Autowired
	private TbUserDao tbUserDao;

	public TbJoin get(String id) {
		return super.get(id);
	}
	
	public List<TbJoin> findList(TbJoin tbJoin) {
		return super.findList(tbJoin);
	}
	
	public Page<TbJoin> findPage(Page<TbJoin> page, TbJoin tbJoin) {
		return super.findPage(page, tbJoin);
	}
	
	@Transactional(readOnly = false)
	public void save(TbJoin tbJoin) {
		super.save(tbJoin);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbJoin tbJoin) {
		super.delete(tbJoin);
	}

	public AppResult getDepartmentList(){
		List<JoinDepartment> list = tbJoinDao.getDepartmentList();
		if(list!=null && list.size()!=0){
			return AppResult.ok(list);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.JOIN_DEPARTMENT_ERROR_MSG, null);
		}
	}

	public AppResult saveTbJoin(JoinInfo joinInfo){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String comeTime1 = joinInfo.getComeTime1();
			String goTime1 = joinInfo.getGoTime1();
			if(comeTime1!=null && !("").equals(comeTime1)){
				System.out.println("comeTime1:"+comeTime1);
				System.out.println("comeTime:"+sdf.parse(comeTime1.replace("T"," ")));
				joinInfo.setComeTime(sdf.parse(comeTime1.replace("T"," ")));
			}
			if(goTime1!=null && !("").equals(goTime1)){
				System.out.println("goTime1:"+goTime1);
				System.out.println("goTime:"+sdf.parse(goTime1.replace("T"," ")));
				joinInfo.setGoTime(sdf.parse(goTime1.replace("T"," ")));
			}
			joinInfo.setCreateBy(UserUtils.getUser().getId());
			joinInfo.setCreateDate(new Date());
			joinInfo.setUpdateBy(UserUtils.getUser().getId());
			joinInfo.setUpdateDate(new Date());
			joinInfo.setIsComeStation(joinInfo.getIsSendStation());
			//1.修改TB_USER
			tbUserDao.updateTbUser(joinInfo);
			String id = joinInfo.getId();
			if(id==null || ("").equals(id)){
				//2.插入TB_JOIN
				joinInfo.setId(java.util.UUID.randomUUID().toString().replaceAll("\\-", ""));
				tbJoinDao.addTbJoin(joinInfo);
			}else{
				System.out.println("Update______________id:"+id);
				tbJoinDao.updateJoinInfo(joinInfo);
			}

			return AppResult.ok("ok");
		}catch(Exception e){
			e.printStackTrace();
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.JOIN_PART_ERROR_MSG, null);
		}
	}

	public AppResult getJoinInfo(String meetingId, String userId){
		JoinInfo joinInfo = tbJoinDao.getJoinInfo(meetingId, userId);
		if(joinInfo!=null){
            try{
                Date comeTime = joinInfo.getComeTime();
                Date goTime = joinInfo.getGoTime();
                SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd hh:mm");
                //comeTime1:2019-03-25T03:11:00.000Z
                System.out.println("comTimeStr:____"+sdf2.format(comeTime).replace(" ","T"));
                joinInfo.setComeTime1(sdf2.format(comeTime).replace(" ","T")+":00.000Z");
                joinInfo.setGoTime1(sdf2.format(goTime).replace(" ","T")+":00.000Z");
            }catch(Exception e){
                e.printStackTrace();
            }
			return AppResult.ok(joinInfo);
		}else {
			JoinInfo userInfo = tbJoinDao.getUserInfo(userId);
			if(userInfo!=null){
				return AppResult.ok(userInfo);
			}else{
				return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.GET_JOIN_INFO_ERROR_MSG, null);
			}
		}
	}

	public AppResult getMeetingCompanyList(String meetingId){
		List<MeetingCompany> companyList = tbJoinDao.getMeetingCompanyList(meetingId);
		if(companyList!=null && companyList.size()!=0){
			return AppResult.ok(companyList);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.JOIN_DEPARTMENT_ERROR_MSG, null);
		}
	}

}