/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meet;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.common.UUIDMeeting;
import com.lw.modules.meeting.dao.meet.TbInviteCodeDao;
import com.lw.modules.meeting.entity.meet.TbInviteCode;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.InviteCodeModel;
import com.lw.modules.meeting.entity.model.ParamsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邀请码生成Service
 * @author meijx
 * @version 2019-03-07
 */
@Service
@Transactional(readOnly = true)
public class TbInviteCodeService extends CrudService<TbInviteCodeDao, TbInviteCode> {
	@Autowired
	private TbInviteCodeDao tbInviteCodeDao;

	public TbInviteCode get(String id) {
		return super.get(id);
	}
	
	public List<TbInviteCode> findList(TbInviteCode tbInviteCode) {
		return super.findList(tbInviteCode);
	}
	
	public Page<TbInviteCode> findPage(Page<TbInviteCode> page, TbInviteCode tbInviteCode) {
		return super.findPage(page, tbInviteCode);
	}
	
	@Transactional(readOnly = false)
	public void save(TbInviteCode tbInviteCode) {
		if (StringUtils.isNotBlank(tbInviteCode.getInviteCode())) {
			int num = Integer.parseInt(tbInviteCode.getInviteCode());

			for(int i=0;i<num;i++){
				TbInviteCode code = new TbInviteCode();
				code.setInviteCode(UUIDMeeting.generateUuid8());
				code.setMeetingId(tbInviteCode.getMeetingId());
				code.setStatus("0");

				super.save(code);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbInviteCode tbInviteCode) {
		super.delete(tbInviteCode);
	}

	@Transactional(readOnly = false)
    public AppResult checkMeeting(ParamsModel paramsModel) {
		InviteCodeModel inviteCodeModel = tbInviteCodeDao.checkCode(paramsModel);
		if(inviteCodeModel != null){
			//inviteCodeModel.setPhone(paramsModel.getPhone());
			//inviteCodeModel.setDate(new Date());
			//tbInviteCodeDao.updateCodeStatus(inviteCodeModel);
			return AppResult.ok(inviteCodeModel);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.CHECK_MEETINGCODE_MSG, null);
		}
    }

	/**
	 * 改变会议状态并绑定用户手机号
	 */
	@Transactional(readOnly = false)
	public AppResult bindingCode(ParamsModel paramsModel) {
		tbInviteCodeDao.bindingCode(paramsModel);
		return AppResult.ok();
	}
}