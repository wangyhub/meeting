/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lw.modules.meeting.common.MeetingConstant;
import com.lw.modules.meeting.entity.message.TbMessageRecord;
import com.lw.modules.meeting.service.message.TbMessageRecordService;
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
import com.lw.modules.meeting.entity.message.TbMessage;
import com.lw.modules.meeting.service.message.TbMessageService;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息信息Controller
 * @author wangyi
 * @version 2019-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/message/tbMessage")
public class TbMessageController extends BaseController {

	@Autowired
	private TbMessageService tbMessageService;

	@Autowired
	private TbMessageRecordService tbMessageRecordService;
	
	@ModelAttribute
	public TbMessage get(@RequestParam(required=false) String id) {
		TbMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMessageService.get(id);
		}
		if (entity == null){
			entity = new TbMessage();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbMessage tbMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMessage.setMeetingId((String)meetingId);
		}
		Page<TbMessage> page = tbMessageService.findPage(new Page<TbMessage>(request, response), tbMessage); 
		model.addAttribute("page", page);
		return "modules/meeting/message/tbMessageList";
	}

	@RequestMapping(value = "form")
	public String form(TbMessage tbMessage, Model model) {
		model.addAttribute("tbMessage", tbMessage);
		return "modules/meeting/message/tbMessageForm";
	}

	@RequestMapping(value = "view")
	public String view(TbMessage tbMessage, Model model) {
		TbMessageRecord condition = new TbMessageRecord();
		condition.setMessageId(tbMessage.getId());
		List<TbMessageRecord> list = tbMessageRecordService.findList(condition);
		List recordList = new ArrayList();
		for(TbMessageRecord record:list){
			JSONObject obj = new JSONObject();
			obj.put("id",record.getUserId());
			obj.put("text",record.getUserName());
			recordList.add(obj);
		}
		model.addAttribute("recordList", recordList);
		model.addAttribute("tbMessage", tbMessage);
		return "modules/meeting/message/tbMessageFormView";
	}

	@RequestMapping(value = "save")
	public String save(TbMessage tbMessage, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Object meetingId = request.getSession().getAttribute(MeetingConstant.SESSION_MEETING_ID);
		if(meetingId != null) {
			tbMessage.setMeetingId((String)meetingId);
			tbMessageService.save(tbMessage);
			addMessage(redirectAttributes, "保存消息信息成功");
		}
		else{
			addMessage(redirectAttributes, "保存消息信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/meeting/message/tbMessage/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbMessage tbMessage, RedirectAttributes redirectAttributes) {
		tbMessageService.delete(tbMessage);
		addMessage(redirectAttributes, "删除消息信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/message/tbMessage/?repage";
	}

}