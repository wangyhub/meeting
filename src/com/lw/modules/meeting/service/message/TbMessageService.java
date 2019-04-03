/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.message;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.common.JiguangHandle;
import com.lw.modules.meeting.common.SmsHandle;
import com.lw.modules.meeting.dao.message.TbMessageDao;
import com.lw.modules.meeting.dao.message.TbMessageRecordDao;
import com.lw.modules.meeting.entity.message.TbMessage;
import com.lw.modules.meeting.entity.message.TbMessageRecord;
import com.lw.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息信息Service
 * @author wangyi
 * @version 2019-03-14
 */
@Service
@Transactional(readOnly = true)
public class TbMessageService extends CrudService<TbMessageDao, TbMessage> {

	@Autowired
	private TbMessageRecordDao tbMessageRecordDao;

	public TbMessage get(String id) {
		return super.get(id);
	}
	
	public List<TbMessage> findList(TbMessage tbMessage) {
		return super.findList(tbMessage);
	}
	
	public Page<TbMessage> findPage(Page<TbMessage> page, TbMessage tbMessage) {
		return super.findPage(page, tbMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(TbMessage tbMessage) {
		tbMessage.setSendTime(new Date());
		super.save(tbMessage);

		String sendNum = tbMessage.getSendNum();
		if(sendNum!=null && !"".equals(sendNum)) {
			String[] ids = sendNum.split(",");
			List<String> aliasList = new ArrayList<String>();
			for (int i = 0; i < ids.length; i++) {
				String[] idphone = ids[i].split("\\|");
				TbMessageRecord record = new TbMessageRecord();
				record.setMessageId(tbMessage.getId());
				record.setUserId(idphone[0]);
				record.setUserPhone(idphone[1]);
				record.setMessageTime(new Date());
				record.preInsert();

				aliasList.add(idphone[0]);

				if("1".equals(tbMessage.getType())) {		//发送短信
//					String smsMessage = SmsHandle.getSmsMessage(tbMessage.getContent(), idphone[1]);
//					String status = SmsHandle.sendSms(smsMessage);
//					record.setStatus(status);
				}

				tbMessageRecordDao.insert(record);
			}

			if("2".equals(tbMessage.getType())) {        //app推送
				String title = DictUtils.getDictLabel(tbMessage.getKind(), "message_kind", "其他消息");
				JiguangHandle.sendToAppList(aliasList, title, tbMessage.getContent());
			}
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(TbMessage tbMessage) {
		super.delete(tbMessage);
	}
	
}