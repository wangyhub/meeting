/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.stay;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.stay.TbJoinStay;
import com.lw.modules.meeting.dao.stay.TbJoinStayDao;

/**
 * 用户住宿Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbJoinStayService extends CrudService<TbJoinStayDao, TbJoinStay> {

	public TbJoinStay get(String id) {
		return super.get(id);
	}
	
	public List<TbJoinStay> findList(TbJoinStay tbJoinStay) {
		return super.findList(tbJoinStay);
	}
	
	public Page<TbJoinStay> findPage(Page<TbJoinStay> page, TbJoinStay tbJoinStay) {
		return super.findPage(page, tbJoinStay);
	}
	
	@Transactional(readOnly = false)
	public void save(TbJoinStay tbJoinStay) {
		String stayId = tbJoinStay.getStayId();
		String joinIds = tbJoinStay.getJoinId();

		if(joinIds!=null && !"".equals(joinIds)){
			String[] ids = joinIds.split(",");
			for(int i=0;i<ids.length;i++){
				TbJoinStay stay = new TbJoinStay();
				stay.setJoinId(ids[i]);
				stay.setStayId(stayId);
				super.save(stay);
			}
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(TbJoinStay tbJoinStay) {
		super.delete(tbJoinStay);
	}
	
}