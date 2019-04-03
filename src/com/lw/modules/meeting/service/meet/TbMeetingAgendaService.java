/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import java.util.List;

import com.lw.modules.meeting.entity.model.AgendaDetail;
import com.lw.modules.meeting.entity.model.AgendaInfo;
import com.lw.modules.meeting.entity.model.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.modules.meeting.common.AppConstant;
import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.entity.meet.TbMeetingAgenda;
import com.lw.modules.meeting.dao.meet.TbMeetingAgendaDao;
import com.lw.modules.meeting.entity.meet.TbAgendaDetail;
import com.lw.modules.meeting.dao.meet.TbAgendaDetailDao;

/**
 * 日程安排Service
 * @author meijx
 * @version 2019-03-11
 */
@Service
@Transactional(readOnly = true)
public class TbMeetingAgendaService extends CrudService<TbMeetingAgendaDao, TbMeetingAgenda> {

	@Autowired
	private TbAgendaDetailDao tbAgendaDetailDao;

	@Autowired
	private TbMeetingAgendaDao tbMeetingAgendaDao;

	public TbMeetingAgenda get(String id) {
		TbMeetingAgenda tbMeetingAgenda = super.get(id);
		tbMeetingAgenda.setTbAgendaDetailList(tbAgendaDetailDao.findListByAgendaId(id));
		return tbMeetingAgenda;
	}
	
	public List<TbMeetingAgenda> findList(TbMeetingAgenda tbMeetingAgenda) {
		return super.findList(tbMeetingAgenda);
	}
	
	public Page<TbMeetingAgenda> findPage(Page<TbMeetingAgenda> page, TbMeetingAgenda tbMeetingAgenda) {
		return super.findPage(page, tbMeetingAgenda);
	}
	
	@Transactional(readOnly = false)
	public void save(TbMeetingAgenda tbMeetingAgenda) {
		super.save(tbMeetingAgenda);
		for (TbAgendaDetail tbAgendaDetail : tbMeetingAgenda.getTbAgendaDetailList()){
			if (tbAgendaDetail.getId() == null){
				continue;
			}
			if (TbAgendaDetail.DEL_FLAG_NORMAL.equals(tbAgendaDetail.getDelFlag())){
				if (StringUtils.isBlank(tbAgendaDetail.getId())){
					tbAgendaDetail.setAgendaId(tbMeetingAgenda.getId());
					tbAgendaDetail.preInsert();
					tbAgendaDetailDao.insert(tbAgendaDetail);
				}else{
					tbAgendaDetail.preUpdate();
					tbAgendaDetailDao.update(tbAgendaDetail);
				}
			}else{
				tbAgendaDetailDao.delete(tbAgendaDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbMeetingAgenda tbMeetingAgenda) {
		super.delete(tbMeetingAgenda);
		TbAgendaDetail detail = new TbAgendaDetail();
		detail.setAgendaId(tbMeetingAgenda.getId());
		tbAgendaDetailDao.delete(detail);
	}

	public AppResult getAgendaList(String meetingId){
		//List<TbMeetingAgenda> agendaList = super.findList(tbMeetingAgenda);
		List<AgendaInfo> agendaList = tbMeetingAgendaDao.getAgendaList(meetingId);
		if(agendaList!=null && agendaList.size()!=0){
			return AppResult.ok(agendaList);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.AGENDA_ERROR_MSG, null);
		}
	}

	public AppResult getAgendaInfo(String agendaId){
		AgendaInfo agendaInfo = tbMeetingAgendaDao.getAgendaInfoById(agendaId);
		List<AgendaDetail> list = tbAgendaDetailDao.getAgendaListByAgendaId(agendaId);
		agendaInfo.setDetailList(list);
		if(agendaInfo!=null){
			return AppResult.ok(agendaInfo);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.AGENDA_ERROR_MSG, null);
		}
	}

}