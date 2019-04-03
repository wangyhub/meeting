/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.stay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.stay.TbHotel;
import com.lw.modules.meeting.entity.stay.TbJoinStay;
import com.lw.modules.meeting.entity.trip.TbJoinTrip;
import com.lw.modules.meeting.service.stay.TbHotelService;
import com.lw.modules.meeting.service.stay.TbJoinStayService;
import net.sf.json.JSONObject;
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
import com.lw.modules.meeting.entity.stay.TbStay;
import com.lw.modules.meeting.service.stay.TbStayService;

import java.util.ArrayList;
import java.util.List;

/**
 * 住宿信息Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/stay/tbStay")
public class TbStayController extends BaseController {

	@Autowired
	private TbStayService tbStayService;

	@Autowired
	private TbHotelService tbHotelService;

	@Autowired
	private TbJoinStayService tbJoinStayService;
	
	@ModelAttribute
	public TbStay get(@RequestParam(required=false) String id) {
		TbStay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbStayService.get(id);
		}
		if (entity == null){
			entity = new TbStay();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbStay tbStay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbStay.setMeetingId((String)meetingId);
		}
		Page<TbStay> page = tbStayService.findPage(new Page<TbStay>(request, response), tbStay); 
		model.addAttribute("page", page);
		return "modules/meeting/stay/tbStayList";
	}

	@RequestMapping(value = "form")
	public String form(TbStay tbStay, HttpServletRequest request, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		TbHotel condition = new TbHotel();
		if(meetingId != null) {
			condition.setMeetingId((String)meetingId);
		}
		List<TbHotel> hotelList = tbHotelService.findList(condition);
		model.addAttribute("hotelList", hotelList);

		if(StringUtils.isNotBlank(tbStay.getId())) {
			TbJoinStay cond = new TbJoinStay();
			cond.setStayId(tbStay.getId());
			List<TbJoinStay> list = tbJoinStayService.findList(cond);
			List joinList = new ArrayList();
			for (TbJoinStay join : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", join.getJoinId());
				obj.put("text", join.getUserName());
				joinList.add(obj);
			}
			model.addAttribute("joinList", joinList);
		}
		model.addAttribute("tbStay", tbStay);
		return "modules/meeting/stay/tbStayForm";
	}

	@RequestMapping(value = "save")
	public String save(TbStay tbStay, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbStay.setMeetingId((String) meetingId);
			tbStayService.save(tbStay);
			addMessage(redirectAttributes, "保存住宿信息成功");
		}
		else{
			addMessage(redirectAttributes, "保存住宿信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/stay/tbStay/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbStay tbStay, RedirectAttributes redirectAttributes) {
		String stayId = tbStay.getId();
		TbJoinStay tbJoinStay = new TbJoinStay();
		tbJoinStay.setStayId(stayId);
		tbJoinStayService.delete(tbJoinStay);
		tbStayService.delete(tbStay);
		addMessage(redirectAttributes, "删除住宿信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/stay/tbStay/?repage";
	}

}