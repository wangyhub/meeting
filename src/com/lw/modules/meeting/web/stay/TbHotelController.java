/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.stay;

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
import com.lw.modules.meeting.entity.stay.TbHotel;
import com.lw.modules.meeting.service.stay.TbHotelService;

/**
 * 酒店信息Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/stay/tbHotel")
public class TbHotelController extends BaseController {

	@Autowired
	private TbHotelService tbHotelService;
	
	@ModelAttribute
	public TbHotel get(@RequestParam(required=false) String id) {
		TbHotel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbHotelService.get(id);
		}
		if (entity == null){
			entity = new TbHotel();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbHotel tbHotel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbHotel.setMeetingId((String)meetingId);
		}
		Page<TbHotel> page = tbHotelService.findPage(new Page<TbHotel>(request, response), tbHotel); 
		model.addAttribute("page", page);
		return "modules/meeting/stay/tbHotelList";
	}

	@RequestMapping(value = "form")
	public String form(TbHotel tbHotel, Model model) {
		model.addAttribute("tbHotel", tbHotel);
		return "modules/meeting/stay/tbHotelForm";
	}

	@RequestMapping(value = "save")
	public String save(TbHotel tbHotel, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbHotel.setMeetingId((String)meetingId);
			tbHotelService.save(tbHotel);
			addMessage(redirectAttributes, "保存酒店信息成功");
		}
		else{
			addMessage(redirectAttributes, "保存酒店信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/stay/tbHotel/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbHotel tbHotel, RedirectAttributes redirectAttributes) {
		tbHotelService.delete(tbHotel);
		addMessage(redirectAttributes, "删除酒店信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/stay/tbHotel/?repage";
	}

}