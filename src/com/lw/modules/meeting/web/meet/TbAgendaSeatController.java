/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meet;

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
import com.lw.modules.meeting.entity.meet.TbAgendaSeat;
import com.lw.modules.meeting.service.meet.TbAgendaSeatService;

/**
 * 座次安排Controller
 * @author meijx
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbAgendaSeat")
public class TbAgendaSeatController extends BaseController {

	@Autowired
	private TbAgendaSeatService tbAgendaSeatService;
	
	@ModelAttribute
	public TbAgendaSeat get(@RequestParam(required=false) String id) {
		TbAgendaSeat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbAgendaSeatService.get(id);
		}
		if (entity == null){
			entity = new TbAgendaSeat();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbAgendaSeat tbAgendaSeat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbAgendaSeat> page = tbAgendaSeatService.findPage(new Page<TbAgendaSeat>(request, response), tbAgendaSeat); 
		model.addAttribute("page", page);
		return "modules/meeting/meet/tbAgendaSeatList";
	}

	@RequestMapping(value = "form")
	public String form(TbAgendaSeat tbAgendaSeat, Model model) {
		model.addAttribute("tbAgendaSeat", tbAgendaSeat);
		return "modules/meeting/meet/tbAgendaSeatForm";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public void save(TbAgendaSeat tbAgendaSeat, Model model) {
		tbAgendaSeatService.save(tbAgendaSeat);
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public void delete(TbAgendaSeat tbAgendaSeat, RedirectAttributes redirectAttributes) {
		tbAgendaSeatService.delete(tbAgendaSeat);
	}

}