/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.meal;

import java.util.List;

import com.lw.modules.meeting.entity.stay.TbJoinStay;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.meal.TbJoinMeal;
import com.lw.modules.meeting.dao.meal.TbJoinMealDao;

/**
 * 用户用餐Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbJoinMealService extends CrudService<TbJoinMealDao, TbJoinMeal> {

	public TbJoinMeal get(String id) {
		return super.get(id);
	}
	
	public List<TbJoinMeal> findList(TbJoinMeal tbJoinMeal) {
		return super.findList(tbJoinMeal);
	}
	
	public Page<TbJoinMeal> findPage(Page<TbJoinMeal> page, TbJoinMeal tbJoinMeal) {
		return super.findPage(page, tbJoinMeal);
	}
	
	@Transactional(readOnly = false)
	public void save(TbJoinMeal tbJoinMeal) {
		String mealId = tbJoinMeal.getMealId();
		String joinIds = tbJoinMeal.getJoinId();
		String tableNum = tbJoinMeal.getTableNum();

		if(joinIds!=null && !"".equals(joinIds)){
			String[] ids = joinIds.split(",");
			for(int i=0;i<ids.length;i++){
				TbJoinMeal meal = new TbJoinMeal();
				meal.setJoinId(ids[i]);
				meal.setMealId(mealId);
				meal.setTableNum(tableNum);
				super.save(meal);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbJoinMeal tbJoinMeal) {
		super.delete(tbJoinMeal);
	}
	
}