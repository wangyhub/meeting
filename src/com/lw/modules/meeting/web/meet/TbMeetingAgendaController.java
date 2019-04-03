/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.meet.TbMeetingPlace;
import com.lw.modules.meeting.service.meet.TbMeetingPlaceService;
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
import com.lw.modules.meeting.entity.meet.TbMeetingAgenda;
import com.lw.modules.meeting.service.meet.TbMeetingAgendaService;

import java.util.List;

/**
 * 日程安排Controller
 * @author meijx
 * @version 2019-03-11
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbMeetingAgenda")
public class TbMeetingAgendaController extends BaseController {

	@Autowired
	private TbMeetingAgendaService tbMeetingAgendaService;

	@Autowired
	private TbMeetingPlaceService tbMeetingPlaceService;
	
	@ModelAttribute
	public TbMeetingAgenda get(@RequestParam(required=false) String id) {
		TbMeetingAgenda entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMeetingAgendaService.get(id);
		}
		if (entity == null){
			entity = new TbMeetingAgenda();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbMeetingAgenda tbMeetingAgenda, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		TbMeetingPlace conditon = new TbMeetingPlace();
		if(meetingId != null) {
			tbMeetingAgenda.setMeetingId((String)meetingId);
			conditon.setMeetingId((String)meetingId);
		}
		Page<TbMeetingAgenda> page = tbMeetingAgendaService.findPage(new Page<TbMeetingAgenda>(request, response), tbMeetingAgenda); 
		model.addAttribute("page", page);

		//读取场地列表
		List<TbMeetingPlace> placeList = tbMeetingPlaceService.findList(conditon);
		model.addAttribute("placeList", placeList);

		return "modules/meeting/meet/tbMeetingAgendaList";
	}

	@RequestMapping(value = "form")
	public String form(TbMeetingAgenda tbMeetingAgenda, HttpServletRequest request, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		TbMeetingPlace conditon = new TbMeetingPlace();
		if(meetingId != null) {
			conditon.setMeetingId((String)meetingId);
		}
		//读取场地列表
		List<TbMeetingPlace> placeList = tbMeetingPlaceService.findList(conditon);
		model.addAttribute("placeList", placeList);

		model.addAttribute("tbMeetingAgenda", tbMeetingAgenda);
		return "modules/meeting/meet/tbMeetingAgendaForm";
	}

	@RequestMapping(value = "save")
	public String save(TbMeetingAgenda tbMeetingAgenda, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMeetingAgenda.setMeetingId((String)meetingId);
			tbMeetingAgendaService.save(tbMeetingAgenda);
			addMessage(redirectAttributes, "保存日程安排成功");
		}
		else{
			addMessage(redirectAttributes, "保存日程安排失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbMeetingAgenda/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbMeetingAgenda tbMeetingAgenda, RedirectAttributes redirectAttributes) {
		tbMeetingAgendaService.delete(tbMeetingAgenda);
		addMessage(redirectAttributes, "删除日程安排成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbMeetingAgenda/?repage";
	}

}