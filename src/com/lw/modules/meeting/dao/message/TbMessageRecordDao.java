/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.message;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.message.TbMessageRecord;
import com.lw.modules.meeting.entity.model.ParamsModel;

import java.util.List;

/**
 * 消息记录信息DAO接口
 * @author wangyi
 * @version 2019-03-14
 */
@MyBatisDao
public interface TbMessageRecordDao extends CrudDao<TbMessageRecord> {

    public List<TbMessageRecord> findUserMessage(ParamsModel paramsModel);

    public Integer findNotRead(ParamsModel paramsModel);

    public void isRead(ParamsModel paramsModel);
}