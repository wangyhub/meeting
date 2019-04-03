/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.dao.meet.TbMeetingCompanyDao;
import com.lw.modules.meeting.dao.meet.TbMeetingDao;
import com.lw.modules.meeting.entity.meet.TbMeeting;
import com.lw.modules.meeting.entity.meet.TbMeetingCompany;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.CommonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会议管理Service
 * @author meijx
 * @version 2019-03-06
 */
@Service
@Transactional(readOnly = true)
public class TbMeetingService extends CrudService<TbMeetingDao, TbMeeting> {

	@Autowired
	private TbMeetingDao tbMeetingDao;

	@Autowired
	private TbMeetingCompanyDao tbMeetingCompanyDao;
	
	public TbMeeting get(String id) {
		TbMeeting tbMeeting = super.get(id);
		tbMeeting.setTbMeetingCompanyList(tbMeetingCompanyDao.findListByMeetingId(id));
		return tbMeeting;
	}
	
	public List<TbMeeting> findList(TbMeeting tbMeeting) {
		return super.findList(tbMeeting);
	}
	
	public Page<TbMeeting> findPage(Page<TbMeeting> page, TbMeeting tbMeeting) {
		return super.findPage(page, tbMeeting);
	}
	
	@Transactional(readOnly = false)
	public void save(TbMeeting tbMeeting) {
		super.save(tbMeeting);
		for (TbMeetingCompany tbMeetingCompany : tbMeeting.getTbMeetingCompanyList()){
			if (tbMeetingCompany.getId() == null){
				continue;
			}
			if (TbMeetingCompany.DEL_FLAG_NORMAL.equals(tbMeetingCompany.getDelFlag())){
				if (StringUtils.isBlank(tbMeetingCompany.getId())){
					tbMeetingCompany.setMeetingId(tbMeeting.getId());
					tbMeetingCompany.preInsert();
					tbMeetingCompanyDao.insert(tbMeetingCompany);
				}else{
					tbMeetingCompany.preUpdate();
					tbMeetingCompanyDao.update(tbMeetingCompany);
				}
			}else{
				tbMeetingCompanyDao.delete(tbMeetingCompany);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbMeeting tbMeeting) {
		super.delete(tbMeeting);
        TbMeetingCompany mc = new TbMeetingCompany();
        mc.setMeetingId(tbMeeting.getId());
		tbMeetingCompanyDao.delete(mc);
	}

	@Transactional(readOnly = false)
	public void updateStatus(TbMeeting tbMeeting) {
		tbMeeting.preUpdate();
		tbMeetingDao.updateStatus(tbMeeting);
	}

	/**
	 * 展示大会地图
	 */
    public AppResult showMeetingMap(String meetingId) {
		CommonModel commonModel = tbMeetingDao.showMeetingMap(meetingId);
		if(commonModel != null){
			return AppResult.ok(commonModel);
		}
    	return null;
    }
}