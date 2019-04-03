/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meal;

import java.util.List;

import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.common.AppConstant;
import com.lw.modules.meeting.dao.meal.TbJoinMealDao;
import com.lw.modules.meeting.entity.meal.TbJoinMeal;
import com.lw.modules.meeting.entity.meet.TbJoin;
import com.lw.modules.meeting.entity.model.AppResult;
import com.lw.modules.meeting.entity.model.MealInfo;
import com.lw.modules.meeting.entity.model.MealTable;
import com.lw.modules.meeting.entity.stay.TbJoinStay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.meal.TbMeal;
import com.lw.modules.meeting.dao.meal.TbMealDao;

/**
 * 用餐信息Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbMealService extends CrudService<TbMealDao, TbMeal> {
	@Autowired
	private TbMealDao tbMealDao;

	@Autowired
	private TbJoinMealDao tbJoinMealDao;

	public TbMeal get(String id) {
		return super.get(id);
	}
	
	public List<TbMeal> findList(TbMeal tbMeal) {
		return super.findList(tbMeal);
	}
	
	public Page<TbMeal> findPage(Page<TbMeal> page, TbMeal tbMeal) {
		return super.findPage(page, tbMeal);
	}
	
	@Transactional(readOnly = false)
	public void save(TbMeal tbMeal) {
		super.save(tbMeal);

		String joinNum = tbMeal.getJoinNum();

		if(joinNum!=null && !"".equals(joinNum)){
			TbJoinMeal cond = new TbJoinMeal();
			cond.setMealId(tbMeal.getId());
			tbJoinMealDao.delete(cond);

			String[] ids = joinNum.split(",");
			for(int i=0;i<ids.length;i++){
				String[] tableid = ids[i].split("\\|");
				TbJoinMeal meal = new TbJoinMeal();
				meal.setJoinId(tableid[1]);
				meal.setMealId(tbMeal.getId());
				if(StringUtils.isNotBlank(tableid[0])){
					meal.setTableNum(tableid[0]);
				}
				meal.preInsert();
				tbJoinMealDao.insert(meal);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbMeal tbMeal) {
		super.delete(tbMeal);
	}

	public AppResult getMealList(String meetingId,String userId){
		List<MealInfo> mealList = tbMealDao.getMealInfoListByMeetingId(meetingId,userId);
		if(mealList!=null && mealList.size()!=0){
			return AppResult.ok(mealList);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.MEAL_ERROR_MSG, null);
		}
	}

	public AppResult getMealTable(String mealId, String userId){
		MealTable mealTable = tbMealDao.getMealTableByMealIdAndUserId(mealId,userId);
		if(mealTable!=null){
			return AppResult.ok(mealTable);
		}else{
			return AppResult.build(AppConstant.APP_ERROE_STATUS, AppConstant.MEAL_ERROR_MSG, null);
		}
	}
}