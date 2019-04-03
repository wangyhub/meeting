/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.message;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.message.TbMessage;

/**
 * 消息信息DAO接口
 * @author wangyi
 * @version 2019-03-14
 */
@MyBatisDao
public interface TbMessageDao extends CrudDao<TbMessage> {
	
}