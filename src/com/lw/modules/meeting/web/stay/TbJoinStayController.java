/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.stay;

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
import com.lw.modules.meeting.entity.stay.TbJoinStay;
import com.lw.modules.meeting.service.stay.TbJoinStayService;

/**
 * 用户住宿Controller
 * @author meijx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/stay/tbJoinStay")
public class TbJoinStayController extends BaseController {

	@Autowired
	private TbJoinStayService tbJoinStayService;

	@RequestMapping(value = {"list", ""})
	public String list(TbJoinStay tbJoinStay, HttpServletRequest request, HttpServletResponse response, Model model) {
		String stayId = request.getParameter("stayId");
		if(StringUtils.isNotBlank(stayId)){
			tbJoinStay.setStayId(stayId);
		}
		Page<TbJoinStay> page = tbJoinStayService.findPage(new Page<TbJoinStay>(request, response), tbJoinStay); 
		model.addAttribute("page", page);
		return "modules/meeting/stay/tbJoinStayList";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public void save(TbJoinStay tbJoinStay, Model model, RedirectAttributes redirectAttributes) {
		tbJoinStayService.save(tbJoinStay);
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbJoinStay tbJoinStay, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String stayId = request.getParameter("stayId");
		tbJoinStayService.delete(tbJoinStay);
		addMessage(redirectAttributes, "删除用户住宿成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/stay/tbJoinStay/?repage&stayId="+stayId;
	}

}