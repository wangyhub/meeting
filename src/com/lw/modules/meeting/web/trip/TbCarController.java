/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.trip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
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
import com.lw.modules.meeting.entity.trip.TbCar;
import com.lw.modules.meeting.service.trip.TbCarService;

/**
 * 车辆信息Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/trip/tbCar")
public class TbCarController extends BaseController {

	@Autowired
	private TbCarService tbCarService;
	
	@ModelAttribute
	public TbCar get(@RequestParam(required=false) String id) {
		TbCar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbCarService.get(id);
		}
		if (entity == null){
			entity = new TbCar();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbCar tbCar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbCar.setMeetingId((String)meetingId);
		}
		Page<TbCar> page = tbCarService.findPage(new Page<TbCar>(request, response), tbCar); 
		model.addAttribute("page", page);
		return "modules/meeting/trip/tbCarList";
	}

	@RequestMapping(value = "form")
	public String form(TbCar tbCar, Model model) {
		model.addAttribute("tbCar", tbCar);
		return "modules/meeting/trip/tbCarForm";
	}

	@RequestMapping(value = "save")
	public String save(TbCar tbCar, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbCar.setMeetingId((String)meetingId);
			tbCarService.save(tbCar);
			addMessage(redirectAttributes, "保存车辆信息成功");
		}
		else{
			addMessage(redirectAttributes, "保存车辆信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/trip/tbCar/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbCar tbCar, RedirectAttributes redirectAttributes) {
		tbCarService.delete(tbCar);
		addMessage(redirectAttributes, "删除车辆信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/trip/tbCar/?repage";
	}

}