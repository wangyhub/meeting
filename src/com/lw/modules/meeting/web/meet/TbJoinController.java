/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.dao.meet.TbMeetingCompanyDao;
import com.lw.modules.meeting.entity.meet.TbMeetingCompany;
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
import com.lw.modules.meeting.entity.meet.TbJoin;
import com.lw.modules.meeting.service.meet.TbJoinService;

import java.util.List;

/**
 * 参会人管理Controller
 * @author meijx
 * @version 2019-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbJoin")
public class TbJoinController extends BaseController {

	@Autowired
	private TbJoinService tbJoinService;

	@Autowired
	private TbMeetingCompanyDao tbMeetingCompanyDao;
	
	@ModelAttribute
	public TbJoin get(@RequestParam(required=false) String id) {
		TbJoin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbJoinService.get(id);
		}
		if (entity == null){
			entity = new TbJoin();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbJoin tbJoin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbJoin.setMeetingId((String)meetingId);
		}
		Page<TbJoin> page = tbJoinService.findPage(new Page<TbJoin>(request, response), tbJoin); 
		model.addAttribute("page", page);
		return "modules/meeting/meet/tbJoinList";
	}

	@RequestMapping(value = "chooseJoinList")
	public String chooseJoinList(TbJoin tbJoin, HttpServletRequest request, HttpServletResponse response, Model model) {
		String agendaId = request.getParameter("agendaId");
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbJoin.setMeetingId((String)meetingId);
			tbJoin.setChooseJoin(agendaId);

			List<TbMeetingCompany> companyList = tbMeetingCompanyDao.findListByMeetingId((String)meetingId);
			model.addAttribute("companyList", companyList);
		}
		List<TbJoin> joinList = tbJoinService.findList(tbJoin);
		model.addAttribute("joinList", joinList);
		return "modules/meeting/meet/chooseJoinList";
	}

	@RequestMapping(value = "chooseCommonList")
	public String chooseCommonList(TbJoin tbJoin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbJoin.setMeetingId((String)meetingId);

			List<TbMeetingCompany> companyList = tbMeetingCompanyDao.findListByMeetingId((String)meetingId);
			model.addAttribute("companyList", companyList);
		}
		List<TbJoin> joinList = tbJoinService.findList(tbJoin);
		model.addAttribute("joinList", joinList);
		return "modules/meeting/meet/chooseCommonList";
	}

	@RequestMapping(value = "chooseMealList")
	public String chooseMealList(TbJoin tbJoin, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbJoin.setMeetingId((String)meetingId);

			List<TbMeetingCompany> companyList = tbMeetingCompanyDao.findListByMeetingId((String)meetingId);
			model.addAttribute("companyList", companyList);
		}
		List<TbJoin> joinList = tbJoinService.findList(tbJoin);
		model.addAttribute("joinList", joinList);
		return "modules/meeting/meal/chooseMealList";
	}

	@RequestMapping(value = "view")
	public String view(TbJoin tbJoin, Model model) {
		model.addAttribute("tbJoin", tbJoin);
		return "modules/meeting/meet/tbJoinFormView";
	}


	@RequestMapping(value = "save")
	public String save(TbJoin tbJoin, Model model, RedirectAttributes redirectAttributes) {
		tbJoinService.save(tbJoin);
		addMessage(redirectAttributes, "保存参会人管理成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbJoin/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbJoin tbJoin, RedirectAttributes redirectAttributes) {
		tbJoinService.delete(tbJoin);
		addMessage(redirectAttributes, "删除参会人管理成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbJoin/?repage";
	}

}