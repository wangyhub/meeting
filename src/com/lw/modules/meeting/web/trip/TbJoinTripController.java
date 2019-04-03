/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.trip;

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
import com.lw.modules.meeting.entity.trip.TbJoinTrip;
import com.lw.modules.meeting.service.trip.TbJoinTripService;

/**
 * 用户行程Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/trip/tbJoinTrip")
public class TbJoinTripController extends BaseController {

	@Autowired
	private TbJoinTripService tbJoinTripService;

	@RequestMapping(value = {"list", ""})
	public String list(TbJoinTrip tbJoinTrip, HttpServletRequest request, HttpServletResponse response, Model model) {
		String tripId = request.getParameter("tripId");
		if(StringUtils.isNotBlank(tripId)){
			tbJoinTrip.setTripId(tripId);
		}
		Page<TbJoinTrip> page = tbJoinTripService.findPage(new Page<TbJoinTrip>(request, response), tbJoinTrip); 
		model.addAttribute("page", page);
		return "modules/meeting/trip/tbJoinTripList";
	}

    @ResponseBody
	@RequestMapping(value = "save")
	public void save(TbJoinTrip tbJoinTrip, Model model, RedirectAttributes redirectAttributes) {
		tbJoinTripService.save(tbJoinTrip);
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbJoinTrip tbJoinTrip, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String tripId = request.getParameter("tripId");
		tbJoinTripService.delete(tbJoinTrip);
		addMessage(redirectAttributes, "删除用户行程成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/trip/tbJoinTrip/?repage&tripId="+tripId;
	}

}