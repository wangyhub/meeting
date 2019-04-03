/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meal;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meal.TbMeal;
import com.lw.modules.meeting.entity.model.MealInfo;
import com.lw.modules.meeting.entity.model.MealTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用餐信息DAO接口
 * @author meijx
 * @version 2019-03-13
 */
@MyBatisDao
public interface TbMealDao extends CrudDao<TbMeal> {
    //根据会议ID获取用餐信息列表
    public List<MealInfo> getMealInfoListByMeetingId(@Param("meetingId") String meetingId, @Param("userId") String userId);
    //根据用餐ID和用户ID，查询桌号信息
    public MealTable getMealTableByMealIdAndUserId(@Param("mealId")String mealId, @Param("userId")String userId);
}