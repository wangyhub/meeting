/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import java.util.List;

import com.lw.modules.meeting.entity.model.AgendaInfo;
import com.lw.modules.meeting.entity.model.TbSeatContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.meet.TbMeetingPlace;
import com.lw.modules.meeting.dao.meet.TbMeetingPlaceDao;

/**
 * 会场管理Service
 * @author meijx
 * @version 2019-03-09
 */
@Service
@Transactional(readOnly = true)
public class TbMeetingPlaceService extends CrudService<TbMeetingPlaceDao, TbMeetingPlace> {

	@Autowired
	private TbMeetingPlaceDao tbMeetingPlaceDao;

	public TbMeetingPlace get(String id) {
		return super.get(id);
	}
	
	public List<TbMeetingPlace> findList(TbMeetingPlace tbMeetingPlace) {
		return super.findList(tbMeetingPlace);
	}
	
	public Page<TbMeetingPlace> findPage(Page<TbMeetingPlace> page, TbMeetingPlace tbMeetingPlace) {
		return super.findPage(page, tbMeetingPlace);
	}
	
	@Transactional(readOnly = false)
	public void save(TbMeetingPlace tbMeetingPlace) {
		super.save(tbMeetingPlace);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbMeetingPlace tbMeetingPlace) {
		super.delete(tbMeetingPlace);
	}

	public List<AgendaInfo> findAll(String seatId) {

		List<AgendaInfo> tbSeatContentList =  tbMeetingPlaceDao.findAll(seatId);

		return  tbSeatContentList;
	}

	public TbSeatContent findById(String id,String userId) {
		TbSeatContent tbSeatContent =	tbMeetingPlaceDao.findById(id,userId);
		return  tbSeatContent;
	}
}