/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meal;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meal.TbJoinMeal;

/**
 * 用户用餐DAO接口
 * @author meijx
 * @version 2019-03-13
 */
@MyBatisDao
public interface TbJoinMealDao extends CrudDao<TbJoinMeal> {
	
}