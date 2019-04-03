/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.back;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.back.TbBackstage;

/**
 * 数据维护DAO接口
 * @author meijx
 * @version 2019-03-21
 */
@MyBatisDao
public interface TbBackstageDao extends CrudDao<TbBackstage> {
	
}