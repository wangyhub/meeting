/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.stay;

import java.util.List;

import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.StayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.lw.modules.meeting.dao.stay.TbJoinStayDao;
import com.lw.modules.meeting.entity.stay.TbJoinStay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.stay.TbStay;
import com.lw.modules.meeting.dao.stay.TbStayDao;

/**
 * 住宿信息Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbStayService extends CrudService<TbStayDao, TbStay> {
	@Autowired
	private TbJoinStayDao tbJoinStayDao;

	@Autowired
	TbStayDao tbStayDao;

	public TbStay get(String id) {
		return super.get(id);
	}
	
	public List<TbStay> findList(TbStay tbStay) {
		return super.findList(tbStay);
	}
	
	public Page<TbStay> findPage(Page<TbStay> page, TbStay tbStay) {
		return super.findPage(page, tbStay);
	}
	
	@Transactional(readOnly = false)
	public void save(TbStay tbStay) {
		super.save(tbStay);

		String joinNum = tbStay.getJoinNum();

		if(joinNum!=null && !"".equals(joinNum)){
			TbJoinStay cond = new TbJoinStay();
			cond.setStayId(tbStay.getId());
			tbJoinStayDao.delete(cond);

			String[] ids = joinNum.split(",");
			for(int i=0;i<ids.length;i++){
				TbJoinStay trip = new TbJoinStay();
				trip.setJoinId(ids[i]);
				trip.setStayId(tbStay.getId());
				trip.preInsert();
				tbJoinStayDao.insert(trip);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbStay tbStay) {
		super.delete(tbStay);
	}

	public AppResult getStayInfo (String meetingId, String userId){
		List<StayInfo> stayInfo = tbStayDao.getStayInfo(meetingId, userId);
		if(stayInfo!=null && stayInfo.size()!=0){
			//一个用户可能存在多条住宿信息，暂时先取第一条
			return AppResult.ok(stayInfo.get(0));
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.GET_STAY_ERROR_MSG, null);
		}
	}

}