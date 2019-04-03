/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.message;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.dao.message.TbMessageRecordDao;
import com.lw.modules.meeting.entity.message.TbMessageRecord;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.DataModel;
import com.lw.modules.meeting.entity.model.ParamsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消息记录信息Service
 * @author wangyi
 * @version 2019-03-14
 */
@Service
@Transactional(readOnly = true)
public class TbMessageRecordService extends CrudService<TbMessageRecordDao, TbMessageRecord> {
	@Autowired
	private TbMessageRecordDao tbMessageRecordDao;

	public TbMessageRecord get(String id) {
		return super.get(id);
	}
	
	public List<TbMessageRecord> findList(TbMessageRecord tbMessageRecord) {
		return super.findList(tbMessageRecord);
	}
	
	public Page<TbMessageRecord> findPage(Page<TbMessageRecord> page, TbMessageRecord tbMessageRecord) {
		return super.findPage(page, tbMessageRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(TbMessageRecord tbMessageRecord) {
		super.save(tbMessageRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbMessageRecord tbMessageRecord) {
		super.delete(tbMessageRecord);
	}

	@Transactional(readOnly = false)
	public AppResult findUserMessage(ParamsModel paramsModel) {
		paramsModel.setStatus(AppConstant.NOTREAD_MESSAGE_STATUS);
		paramsModel.setType(AppConstant.MESSAGE_TYPE_PUSH);
		List<TbMessageRecord> list = tbMessageRecordDao.findUserMessage(paramsModel);
		Integer count = tbMessageRecordDao.findNotRead(paramsModel);
		DataModel dataModel = new DataModel();
		dataModel.setList(list);
		dataModel.setParam(count);
		return AppResult.ok(dataModel);
	}

	@Transactional(readOnly = false)
	public AppResult isRead(ParamsModel paramsModel) {
		paramsModel.setStatus(AppConstant.ISTREAD_MESSAGE_STATUS);
		tbMessageRecordDao.isRead(paramsModel);
		return AppResult.ok();
	}

	public AppResult findNotRead(ParamsModel paramsModel) {
		paramsModel.setStatus(AppConstant.NOTREAD_MESSAGE_STATUS);
		Integer count = tbMessageRecordDao.findNotRead(paramsModel);
		DataModel dataModel = new DataModel();
		dataModel.setParam(count);
		return AppResult.ok(dataModel);
	}
}