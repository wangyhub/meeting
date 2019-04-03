/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.meet.TbAgendaSeat;
import com.lw.modules.meeting.dao.meet.TbAgendaSeatDao;

/**
 * 座次安排Service
 * @author meijx
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly = true)
public class TbAgendaSeatService extends CrudService<TbAgendaSeatDao, TbAgendaSeat> {
	@Autowired
	private TbAgendaSeatDao tbAgendaSeatDao;

	public TbAgendaSeat get(String id) {
		return super.get(id);
	}
	
	public List<TbAgendaSeat> findList(TbAgendaSeat tbAgendaSeat) {
		return super.findList(tbAgendaSeat);
	}

	public List<String> findSeatNoList(String agendaId){
		return tbAgendaSeatDao.findSeatNoList(agendaId);
	}

	public Page<TbAgendaSeat> findPage(Page<TbAgendaSeat> page, TbAgendaSeat tbAgendaSeat) {
		return super.findPage(page, tbAgendaSeat);
	}
	
	@Transactional(readOnly = false)
	public void save(TbAgendaSeat tbAgendaSeat) {
		String joinIds = tbAgendaSeat.getJoinId();
		String seatNos = tbAgendaSeat.getSeatNo();
		String agendaId = tbAgendaSeat.getAgendaId();

		if(joinIds!=null && !"".equals(joinIds) && seatNos!=null && !"".equals(seatNos)){
			String[] ids = joinIds.split(",");
			String[] nos = seatNos.split(",");

			for(int i=0;i<ids.length;i++){
				TbAgendaSeat seat = new TbAgendaSeat();
				seat.setAgendaId(agendaId);
				seat.setJoinId(ids[i]);
				seat.setSeatNo(nos[i]);
				super.save(seat);
			}
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(TbAgendaSeat tbAgendaSeat) {
		super.delete(tbAgendaSeat);
	}
	
}