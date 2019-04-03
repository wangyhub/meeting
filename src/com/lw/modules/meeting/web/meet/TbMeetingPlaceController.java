/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.service.meet.TbAgendaSeatService;
import com.lw.modules.meeting.service.meet.TbJoinService;
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
import com.lw.modules.meeting.entity.meet.TbMeetingPlace;
import com.lw.modules.meeting.service.meet.TbMeetingPlaceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会场管理Controller
 * @author meijx
 * @version 2019-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbMeetingPlace")
public class TbMeetingPlaceController extends BaseController {

	@Autowired
	private TbMeetingPlaceService tbMeetingPlaceService;

	@Autowired
	private TbAgendaSeatService tbAgendaSeatService;
	
	@ModelAttribute
	public TbMeetingPlace get(@RequestParam(required=false) String id) {
		TbMeetingPlace entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMeetingPlaceService.get(id);
		}
		if (entity == null){
			entity = new TbMeetingPlace();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbMeetingPlace tbMeetingPlace, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMeetingPlace.setMeetingId((String)meetingId);
		}
		Page<TbMeetingPlace> page = tbMeetingPlaceService.findPage(new Page<TbMeetingPlace>(request, response), tbMeetingPlace); 
		model.addAttribute("page", page);
		return "modules/meeting/meet/tbMeetingPlaceList";
	}

	@RequestMapping(value = "form")
	public String form(TbMeetingPlace tbMeetingPlace, Model model) {
		model.addAttribute("tbMeetingPlace", tbMeetingPlace);
		return "modules/meeting/meet/tbMeetingPlaceForm";
	}

	@RequestMapping(value = "view")
	public String view(TbMeetingPlace tbMeetingPlace, Model model) {
		model.addAttribute("tbMeetingPlace", tbMeetingPlace);
		return "modules/meeting/meet/tbMeetingPlaceFormView";
	}

	@RequestMapping(value = "showSeat")
	public String showSeat(TbMeetingPlace tbMeetingPlace, Model model) {
		return "modules/meeting/meet/showSeatCharts";
	}

	@RequestMapping(value = "setSeat")
	public String setSeat(TbMeetingPlace tbMeetingPlace,HttpServletRequest request, Model model) {
		String agendaId = request.getParameter("agendaId");
		List<String> seatNos = tbAgendaSeatService.findSeatNoList(agendaId);
		String stNo = "",scNo = "";
		Map<String,String> seatMap = new HashMap<String, String>();
		for(String no:seatNos){
			String[] nos = no.split(",");
			if(nos[0].startsWith("0")){
				stNo += "'"+nos[0]+"',";
			}
			else{
				scNo += "'"+nos[0]+"',";
			}
			seatMap.put(nos[0],nos[1]);
		}
		model.addAttribute("stNo", stNo);
		model.addAttribute("scNo", scNo);

		String region = tbMeetingPlace.getRegion();
		List<String> rowList = new ArrayList<String>();
		int row = 1;
		for(int i=0;i<region.length();i++){
			if(region.charAt(i)=='c'){
				rowList.add((row++)+"");
			}
			else{
				rowList.add("");
			}
		}

		model.addAttribute("seatMap", seatMap);
		model.addAttribute("rowList", rowList);

		return "modules/meeting/meet/setSeatCharts";
	}

	@RequestMapping(value = "save")
	public String save(TbMeetingPlace tbMeetingPlace, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMeetingPlace.setMeetingId((String)meetingId);
			tbMeetingPlaceService.save(tbMeetingPlace);
			addMessage(redirectAttributes, "保存会场成功");
		}else{
			addMessage(redirectAttributes, "保存会场失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbMeetingPlace/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbMeetingPlace tbMeetingPlace, RedirectAttributes redirectAttributes) {
		tbMeetingPlaceService.delete(tbMeetingPlace);
		addMessage(redirectAttributes, "删除会场管理成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbMeetingPlace/?repage";
	}

}