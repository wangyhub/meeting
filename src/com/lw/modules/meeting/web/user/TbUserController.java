/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.user;

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
import com.lw.modules.meeting.entity.user.TbUser;
import com.lw.modules.meeting.service.user.TbUserService;

/**
 * 参会用户Controller
 * @author meijx
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/user/tbUser")
public class TbUserController extends BaseController {

	@Autowired
	private TbUserService tbUserService;
	
	@ModelAttribute
	public TbUser get(@RequestParam(required=false) String id) {
		TbUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbUserService.get(id);
		}
		if (entity == null){
			entity = new TbUser();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbUser tbUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbUser> page = tbUserService.findPage(new Page<TbUser>(request, response), tbUser); 
		model.addAttribute("page", page);
		return "modules/meeting/user/tbUserList";
	}

	@RequestMapping(value = "form")
	public String form(TbUser tbUser, Model model) {
		model.addAttribute("tbUser", tbUser);
		return "modules/meeting/user/tbUserForm";
	}

	@RequestMapping(value = "save")
	public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbUser)){
			return form(tbUser, model);
		}
		tbUserService.save(tbUser);
		addMessage(redirectAttributes, "保存参会用户成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/user/tbUser/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbUser tbUser, RedirectAttributes redirectAttributes) {
		tbUserService.delete(tbUser);
		addMessage(redirectAttributes, "删除参会用户成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/user/tbUser/?repage";
	}

}