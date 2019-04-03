/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.meet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.meet.TbJoin;
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
import com.lw.modules.meeting.entity.meet.TbInviteCode;
import com.lw.modules.meeting.service.meet.TbInviteCodeService;

/**
 * 邀请码生成Controller
 * @author meijx
 * @version 2019-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbInviteCode")
public class TbInviteCodeController extends BaseController {

	@Autowired
	private TbInviteCodeService tbInviteCodeService;
	
	@ModelAttribute
	public TbInviteCode get(@RequestParam(required=false) String id) {
		TbInviteCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbInviteCodeService.get(id);
		}
		if (entity == null){
			entity = new TbInviteCode();
		}
		return entity;
	}

	@RequestMapping(value = {"list", ""})
	public String list(TbInviteCode tbInviteCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbInviteCode.setMeetingId((String)meetingId);
		}
		Page<TbInviteCode> page = tbInviteCodeService.findPage(new Page<TbInviteCode>(request, response), tbInviteCode); 
		model.addAttribute("page", page);
		return "modules/meeting/meet/tbInviteCodeList";
	}

	@RequestMapping(value = "save")
	public String save(TbInviteCode tbInviteCode, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbInviteCode.setMeetingId((String)meetingId);
			tbInviteCodeService.save(tbInviteCode);
			addMessage(redirectAttributes, "生成邀请码成功");
		}
		else{
			addMessage(redirectAttributes, "生成邀请码失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbInviteCode/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbInviteCode tbInviteCode, RedirectAttributes redirectAttributes) {
		tbInviteCodeService.delete(tbInviteCode);
		addMessage(redirectAttributes, "删除邀请码生成成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbInviteCode/?repage";
	}

}