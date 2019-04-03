/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.trip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.message.TbMessageRecord;
import com.lw.modules.meeting.entity.trip.TbCar;
import com.lw.modules.meeting.entity.trip.TbJoinTrip;
import com.lw.modules.meeting.service.trip.TbCarService;
import com.lw.modules.meeting.service.trip.TbJoinTripService;
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
import com.lw.modules.meeting.entity.trip.TbTrip;
import com.lw.modules.meeting.service.trip.TbTripService;

import java.util.ArrayList;
import java.util.List;

/**
 * 行程安排Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/trip/tbTrip")
public class TbTripController extends BaseController {

	@Autowired
	private TbTripService tbTripService;

	@Autowired
	private TbCarService tbCarService;

	@Autowired
	private TbJoinTripService tbJoinTripService;
	
	@ModelAttribute
	public TbTrip get(@RequestParam(required=false) String id) {
		TbTrip entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbTripService.get(id);
		}
		if (entity == null){
			entity = new TbTrip();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbTrip tbTrip, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbTrip.setMeetingId((String)meetingId);
		}
		Page<TbTrip> page = tbTripService.findPage(new Page<TbTrip>(request, response), tbTrip); 
		model.addAttribute("page", page);
		return "modules/meeting/trip/tbTripList";
	}

	@RequestMapping(value = "form")
	public String form(TbTrip tbTrip, HttpServletRequest request, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		TbCar condition = new TbCar();
		if(meetingId != null) {
			condition.setMeetingId((String)meetingId);
		}
		List<TbCar> carList = tbCarService.findList(condition);
		model.addAttribute("carList", carList);

		if(StringUtils.isNotBlank(tbTrip.getId())) {
			TbJoinTrip cond = new TbJoinTrip();
			cond.setTripId(tbTrip.getId());
			List<TbJoinTrip> list = tbJoinTripService.findList(cond);
			List joinList = new ArrayList();
			for (TbJoinTrip join : list) {
				JSONObject obj = new JSONObject();
				obj.put("id", join.getJoinId());
				obj.put("text", join.getUserName());
				joinList.add(obj);
			}
			model.addAttribute("joinList", joinList);
		}
		model.addAttribute("tbTrip", tbTrip);
		return "modules/meeting/trip/tbTripForm";
	}

	@RequestMapping(value = "save")
	public String save(TbTrip tbTrip, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbTrip.setMeetingId((String)meetingId);
			tbTripService.save(tbTrip);
			addMessage(redirectAttributes, "保存行程安排成功");
		}
		else{
			addMessage(redirectAttributes, "保存行程安排失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/trip/tbTrip/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbTrip tbTrip, RedirectAttributes redirectAttributes) {
		String tripId = tbTrip.getId();
		TbJoinTrip tbJoinTrip = new TbJoinTrip();
		tbJoinTrip.setTripId(tripId);
		tbJoinTripService.delete(tbJoinTrip);
		tbTripService.delete(tbTrip);
		addMessage(redirectAttributes, "删除行程安排成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/trip/tbTrip/?repage";
	}

}