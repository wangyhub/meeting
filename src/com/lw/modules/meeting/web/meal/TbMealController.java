/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.meal.TbJoinMeal;
import com.lw.modules.meeting.entity.stay.TbJoinStay;
import com.lw.modules.meeting.service.meal.TbJoinMealService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.entity.meal.TbMeal;
import com.lw.modules.meeting.service.meal.TbMealService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用餐信息Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meal/tbMeal")
public class TbMealController extends BaseController {

	@Autowired
	private TbMealService tbMealService;

	@Autowired
	private TbJoinMealService tbJoinMealService;
	
	@ModelAttribute
	public TbMeal get(@RequestParam(required=false) String id) {
		TbMeal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMealService.get(id);
		}
		if (entity == null){
			entity = new TbMeal();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbMeal tbMeal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMeal.setMeetingId((String)meetingId);
		}
		Page<TbMeal> page = tbMealService.findPage(new Page<TbMeal>(request, response), tbMeal); 
		model.addAttribute("page", page);
		return "modules/meeting/meal/tbMealList";
	}

	@RequestMapping(value = "form")
	public String form(TbMeal tbMeal, Model model) {
		if(StringUtils.isNotBlank(tbMeal.getId())) {
			TbJoinMeal cond = new TbJoinMeal();
			cond.setMealId(tbMeal.getId());
			List<TbJoinMeal> list = tbJoinMealService.findList(cond);
			List joinList = new ArrayList();
			for (TbJoinMeal join : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", (join.getTableNum()==null?"":join.getTableNum())+"|"+join.getJoinId());
				obj.put("text", (join.getTableNum()==null?"":join.getTableNum()+"|")+join.getUserName());
				joinList.add(obj);
			}
			model.addAttribute("joinList", joinList);
		}

		model.addAttribute("tbMeal", tbMeal);
		return "modules/meeting/meal/tbMealForm";
	}

	@RequestMapping(value = "save")
	public String save(TbMeal tbMeal, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMeal.setMeetingId((String) meetingId);
			tbMealService.save(tbMeal);
			addMessage(redirectAttributes, "保存用餐信息成功");
		}
		else{
			addMessage(redirectAttributes, "保存用餐信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/meal/tbMeal/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbMeal tbMeal, RedirectAttributes redirectAttributes) {
		String mealId = tbMeal.getId();
		TbJoinMeal tbJoinMeal = new TbJoinMeal();
		tbJoinMeal.setMealId(mealId);
		tbJoinMealService.delete(tbJoinMeal);
		tbMealService.delete(tbMeal);
		addMessage(redirectAttributes, "删除用餐信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meal/tbMeal/?repage";
	}

}