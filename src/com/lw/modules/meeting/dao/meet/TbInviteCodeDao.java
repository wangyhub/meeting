/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbInviteCode;
import com.lw.modules.meeting.entity.model.InviteCodeModel;
import com.lw.modules.meeting.entity.model.ParamsModel;

/**
 * 邀请码生成DAO接口
 * @author meijx
 * @version 2019-03-07
 */
@MyBatisDao
public interface TbInviteCodeDao extends CrudDao<TbInviteCode> {

    public InviteCodeModel checkCode(ParamsModel paramsModel);

    public void updateCodeStatus(InviteCodeModel inviteCodeModel);

    public void bindingCode(ParamsModel paramsModel);
}