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
import com.lw.modules.meeting.entity.message.TbValidateCode;
import com.lw.modules.meeting.service.message.TbValidateCodeService;

/**
 * 短信验证码Controller
 * @author meijx
 * @version 2019-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/message/tbValidateCode")
public class TbValidateCodeController extends BaseController {

	@Autowired
	private TbValidateCodeService tbValidateCodeService;
	
	@ModelAttribute
	public TbValidateCode get(@RequestParam(required=false) String id) {
		TbValidateCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbValidateCodeService.get(id);
		}
		if (entity == null){
			entity = new TbValidateCode();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:message:tbValidateCode:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbValidateCode tbValidateCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbValidateCode> page = tbValidateCodeService.findPage(new Page<TbValidateCode>(request, response), tbValidateCode); 
		model.addAttribute("page", page);
		return "modules/meeting/message/tbValidateCodeList";
	}

	@RequiresPermissions("meeting:message:tbValidateCode:view")
	@RequestMapping(value = "form")
	public String form(TbValidateCode tbValidateCode, Model model) {
		model.addAttribute("tbValidateCode", tbValidateCode);
		return "modules/meeting/message/tbValidateCodeForm";
	}

	@RequiresPermissions("meeting:message:tbValidateCode:edit")
	@RequestMapping(value = "save")
	public String save(TbValidateCode tbValidateCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbValidateCode)){
			return form(tbValidateCode, model);
		}
		tbValidateCodeService.save(tbValidateCode);
		addMessage(redirectAttributes, "保存短信验证码成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/message/tbValidateCode/?repage";
	}
	
	@RequiresPermissions("meeting:message:tbValidateCode:edit")
	@RequestMapping(value = "delete")
	public String delete(TbValidateCode tbValidateCode, RedirectAttributes redirectAttributes) {
		tbValidateCodeService.delete(tbValidateCode);
		addMessage(redirectAttributes, "删除短信验证码成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/message/tbValidateCode/?repage";
	}

}