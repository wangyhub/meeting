/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.trip;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.trip.TbJoinTrip;
import com.lw.modules.meeting.dao.trip.TbJoinTripDao;

/**
 * 用户行程Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbJoinTripService extends CrudService<TbJoinTripDao, TbJoinTrip> {

	public TbJoinTrip get(String id) {
		return super.get(id);
	}
	
	public List<TbJoinTrip> findList(TbJoinTrip tbJoinTrip) {
		return super.findList(tbJoinTrip);
	}
	
	public Page<TbJoinTrip> findPage(Page<TbJoinTrip> page, TbJoinTrip tbJoinTrip) {
		return super.findPage(page, tbJoinTrip);
	}
	
	@Transactional(readOnly = false)
	public void save(TbJoinTrip tbJoinTrip) {
		String tripId = tbJoinTrip.getTripId();
		String joinIds = tbJoinTrip.getJoinId();

		if(joinIds!=null && !"".equals(joinIds)){
			String[] ids = joinIds.split(",");
			for(int i=0;i<ids.length;i++){
				TbJoinTrip trip = new TbJoinTrip();
				trip.setJoinId(ids[i]);
				trip.setTripId(tripId);
				super.save(trip);
			}
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(TbJoinTrip tbJoinTrip) {
		super.delete(tbJoinTrip);
	}
	
}