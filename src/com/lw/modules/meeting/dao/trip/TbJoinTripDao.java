/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.trip;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.trip.TbJoinTrip;

/**
 * 用户行程DAO接口
 * @author meijx
 * @version 2019-03-13
 */
@MyBatisDao
public interface TbJoinTripDao extends CrudDao<TbJoinTrip> {
	
}