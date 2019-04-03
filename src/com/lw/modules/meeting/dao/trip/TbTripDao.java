/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.trip;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.model.TbTripContent;
import com.lw.modules.meeting.entity.trip.TbTrip;

import java.util.List;

/**
 * 行程安排DAO接口
 * @author meijx
 * @version 2019-03-13
 */
@MyBatisDao
public interface TbTripDao extends CrudDao<TbTrip> {

    List<TbTripContent> findAll(String tripId);
}