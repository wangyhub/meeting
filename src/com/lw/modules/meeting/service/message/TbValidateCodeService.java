/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.message;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.common.SmsHandle;
import com.lw.modules.meeting.dao.message.TbValidateCodeDao;
import com.lw.modules.meeting.entity.message.TbValidateCode;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.CommonModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 短信验证码Service
 * @author meijx
 * @version 2019-03-19
 */
@Service
@Transactional(readOnly = true)
public class TbValidateCodeService extends CrudService<TbValidateCodeDao, TbValidateCode> {

	public TbValidateCode get(String id) {
		return super.get(id);
	}
	
	public List<TbValidateCode> findList(TbValidateCode tbValidateCode) {
		return super.findList(tbValidateCode);
	}
	
	public Page<TbValidateCode> findPage(Page<TbValidateCode> page, TbValidateCode tbValidateCode) {
		return super.findPage(page, tbValidateCode);
	}
	
	@Transactional(readOnly = false)
	public void save(TbValidateCode tbValidateCode) {
		super.save(tbValidateCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbValidateCode tbValidateCode) {
		super.delete(tbValidateCode);
	}

	public AppResult sendCode(String phone) {
		String code = SmsHandle.getSmsCode();
		System.out.println("code="+code);
		String smsMessage = SmsHandle.getSmsMessage("您的验证码为："+code, phone);
		String status = SmsHandle.sendSms(smsMessage);
		System.out.println("status="+status);
		if("1".equals(status)){
			TbValidateCode tbValidateCode = new TbValidateCode();
			tbValidateCode.setMessageTime(new Date());
			tbValidateCode.setUserPhone(phone);
			tbValidateCode.setValidateCode(code);
			this.save(tbValidateCode);
			CommonModel commonModel = new CommonModel();
			commonModel.setSmsCode(code);
			commonModel.setPhone(phone);
			return AppResult.ok(commonModel);
		}
		return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.SMS_CODE_MISS, null);
	}
}