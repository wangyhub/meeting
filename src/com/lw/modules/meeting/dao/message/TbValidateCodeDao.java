/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.message;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.message.TbValidateCode;

/**
 * 短信验证码DAO接口
 * @author meijx
 * @version 2019-03-19
 */
@MyBatisDao
public interface TbValidateCodeDao extends CrudDao<TbValidateCode> {
	
}