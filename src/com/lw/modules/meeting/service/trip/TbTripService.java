/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.trip;

import java.util.List;

import com.lw.modules.meeting.entity.model.TbTripContent;
import org.springframework.beans.factory.annotation.Autowired;
import com.lw.modules.meeting.dao.trip.TbJoinTripDao;
import com.lw.modules.meeting.entity.trip.TbJoinTrip;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.trip.TbTrip;
import com.lw.modules.meeting.dao.trip.TbTripDao;

/**
 * 行程安排Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbTripService extends CrudService<TbTripDao, TbTrip> {
	@Autowired
	private TbJoinTripDao tbJoinTripDao;

	@Autowired
	private TbTripDao tbTripDao;

	public TbTrip get(String id) {
		return super.get(id);
	}
	
	public List<TbTrip> findList(TbTrip tbTrip) {
		return super.findList(tbTrip);
	}
	
	public Page<TbTrip> findPage(Page<TbTrip> page, TbTrip tbTrip) {
		return super.findPage(page, tbTrip);
	}
	
	@Transactional(readOnly = false)
	public void save(TbTrip tbTrip) {
		super.save(tbTrip);
		String joinNum = tbTrip.getJoinNum();

		if(joinNum!=null && !"".equals(joinNum)){
			TbJoinTrip cond = new TbJoinTrip();
			cond.setTripId(tbTrip.getId());
			tbJoinTripDao.delete(cond);

			String[] ids = joinNum.split(",");
			for(int i=0;i<ids.length;i++){
				TbJoinTrip trip = new TbJoinTrip();
				trip.setJoinId(ids[i]);
				trip.setTripId(tbTrip.getId());
				trip.preInsert();
				tbJoinTripDao.insert(trip);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbTrip tbTrip) {
		super.delete(tbTrip);
	}

	public List<TbTripContent> findAll(String tripId) {

		List<TbTripContent> tripContentList  =	tbTripDao.findAll(tripId);

		return  tripContentList;
	}
}