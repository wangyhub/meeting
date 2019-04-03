/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lw.modules.meeting.entity.message.TbMessageRecord;
import com.lw.modules.meeting.service.message.TbMessageRecordService;

/**
 * 消息记录信息Controller
 * @author wangyi
 * @version 2019-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/message/tbMessageRecord")
public class TbMessageRecordController extends BaseController {

	@Autowired
	private TbMessageRecordService tbMessageRecordService;
	
	@ModelAttribute
	public TbMessageRecord get(@RequestParam(required=false) String id) {
		TbMessageRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbMessageRecordService.get(id);
		}
		if (entity == null){
			entity = new TbMessageRecord();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbMessageRecord tbMessageRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbMessageRecord> page = tbMessageRecordService.findPage(new Page<TbMessageRecord>(request, response), tbMessageRecord); 
		model.addAttribute("page", page);
		return "modules/meeting/message/tbMessageRecordList";
	}

	@RequestMapping(value = "form")
	public String form(TbMessageRecord tbMessageRecord, Model model) {
		model.addAttribute("tbMessageRecord", tbMessageRecord);
		return "modules/meeting/message/tbMessageRecordForm";
	}

	@RequestMapping(value = "save")
	public String save(TbMessageRecord tbMessageRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbMessageRecord)){
			return form(tbMessageRecord, model);
		}
		tbMessageRecordService.save(tbMessageRecord);
		addMessage(redirectAttributes, "保存消息记录信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/message/tbMessageRecord/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbMessageRecord tbMessageRecord, RedirectAttributes redirectAttributes) {
		tbMessageRecordService.delete(tbMessageRecord);
		addMessage(redirectAttributes, "删除消息记录信息成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/message/tbMessageRecord/?repage";
	}

}