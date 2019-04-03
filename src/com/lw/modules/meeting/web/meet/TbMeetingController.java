/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.common.servlet.ValidateCodeServlet;
import com.lw.common.utils.IdGen;
import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.meet.TbCreateCode;
import com.lw.modules.meeting.service.meet.TbCreateCodeService;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.utils.UserUtils;
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
import com.lw.modules.meeting.entity.meet.TbMeeting;
import com.lw.modules.meeting.service.meet.TbMeetingService;

/**
 * 会议管理Controller
 * @author meijx
 * @version 2019-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbMeeting")
public class TbMeetingController extends BaseController {

	@Autowired
	private TbMeetingService tbMeetingService;

	@Autowired
	private TbCreateCodeService tbCreateCodeService;
	
	@ModelAttribute
	public TbMeeting get(@RequestParam(required=false) String id) {
		TbMeeting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMeetingService.get(id);
		}
		if (entity == null){
			entity = new TbMeeting();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbMeeting tbMeeting, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		tbMeeting.setCreateBy(user);
		Page<TbMeeting> page = tbMeetingService.findPage(new Page<TbMeeting>(request, response), tbMeeting); 
		model.addAttribute("page", page);
		return "modules/meeting/meet/tbMeetingList";
	}

	@RequestMapping(value = "form")
	public String form(TbMeeting tbMeeting, Model model) {
		model.addAttribute("tbMeeting", tbMeeting);
		return "modules/meeting/meet/tbMeetingForm";
	}

	@RequestMapping(value = "view")
	public String view(TbMeeting tbMeeting, Model model) {
		model.addAttribute("tbMeeting", tbMeeting);
		return "modules/meeting/meet/tbMeetingFormView";
	}

	@RequestMapping(value = "save")
	public String save(TbMeeting tbMeeting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbMeeting)){
			return form(tbMeeting, model);
		}
		tbMeetingService.save(tbMeeting);
		TbCreateCode code = new TbCreateCode();
		code.setCreateCode(tbMeeting.getCreateCode());
		code.setStatus("1");
		tbCreateCodeService.update(code);
		addMessage(redirectAttributes, "保存会议成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbMeeting/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbMeeting tbMeeting, RedirectAttributes redirectAttributes) {
		tbMeetingService.delete(tbMeeting);
		TbCreateCode code = new TbCreateCode();
		code.setCreateCode(tbMeeting.getCreateCode());
		code.setStatus("0");
		tbCreateCodeService.update(code);
		addMessage(redirectAttributes, "删除会议成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbMeeting/?repage";
	}

	@RequestMapping(value = "submit")
	public String submit(TbMeeting tbMeeting, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if ("1".equals(tbMeeting.getStatus())) {
			tbMeetingService.updateStatus(tbMeeting);
		}

		//将会议ID存储到session中
		request.getSession().setAttribute(MeetingConstant.SESSION_MEETING_ID, tbMeeting.getId());
		return "modules/sys/sysIndex";
	}

	@RequestMapping(value = "map")
	public String map(TbMeeting tbMeeting, Model model) {
		return "modules/meeting/meet/baiduMap";
	}

}