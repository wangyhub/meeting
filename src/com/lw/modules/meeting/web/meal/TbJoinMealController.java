/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.entity.meal.TbJoinMeal;
import com.lw.modules.meeting.service.meal.TbJoinMealService;

/**
 * 用户用餐Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meal/tbJoinMeal")
public class TbJoinMealController extends BaseController {

	@Autowired
	private TbJoinMealService tbJoinMealService;

	@RequestMapping(value = {"list", ""})
	public String list(TbJoinMeal tbJoinMeal, HttpServletRequest request, HttpServletResponse response, Model model) {
		String mealId = request.getParameter("mealId");
		if(StringUtils.isNotBlank(mealId)){
			tbJoinMeal.setMealId(mealId);
		}
		Page<TbJoinMeal> page = tbJoinMealService.findPage(new Page<TbJoinMeal>(request, response), tbJoinMeal); 
		model.addAttribute("page", page);
		return "modules/meeting/meal/tbJoinMealList";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public void save(TbJoinMeal tbJoinMeal, Model model, RedirectAttributes redirectAttributes) {
		tbJoinMealService.save(tbJoinMeal);
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbJoinMeal tbJoinMeal, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String mealId = request.getParameter("mealId");
		String isTable = request.getParameter("isTable");
		tbJoinMealService.delete(tbJoinMeal);
		addMessage(redirectAttributes, "删除用户用餐成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meal/tbJoinMeal/?repage&mealId="+mealId+"&isTable="+isTable;
	}

}