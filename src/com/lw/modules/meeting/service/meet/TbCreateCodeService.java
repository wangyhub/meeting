/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import java.util.List;

import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.common.UUIDMeeting;
import com.lw.modules.meeting.entity.meet.TbInviteCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.meet.TbCreateCode;
import com.lw.modules.meeting.dao.meet.TbCreateCodeDao;

/**
 * 会议创建码Service
 * @author meijx
 * @version 2019-03-25
 */
@Service
@Transactional(readOnly = true)
public class TbCreateCodeService extends CrudService<TbCreateCodeDao, TbCreateCode> {
	@Autowired
	private TbCreateCodeDao tbCreateCodeDao;

	public TbCreateCode get(String id) {
		return super.get(id);
	}
	
	public List<TbCreateCode> findList(TbCreateCode tbCreateCode) {
		return super.findList(tbCreateCode);
	}
	
	public Page<TbCreateCode> findPage(Page<TbCreateCode> page, TbCreateCode tbCreateCode) {
		return super.findPage(page, tbCreateCode);
	}
	
	@Transactional(readOnly = false)
	public void save(TbCreateCode tbCreateCode) {
		if (StringUtils.isNotBlank(tbCreateCode.getCreateCode())) {
			int num = Integer.parseInt(tbCreateCode.getCreateCode());

			for(int i=0;i<num;i++){
				TbCreateCode code = new TbCreateCode();
				code.setCreateCode(UUIDMeeting.generateUuid16());
				code.setStatus("0");
				super.save(code);
			}
		}
	}

	@Transactional(readOnly = false)
	public void update(TbCreateCode tbCreateCode) {
		tbCreateCodeDao.update(tbCreateCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbCreateCode tbCreateCode) {
		super.delete(tbCreateCode);
	}
	
}