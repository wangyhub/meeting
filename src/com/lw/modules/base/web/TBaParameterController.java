/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

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
import com.lw.modules.base.entity.TBaParameter;
import com.lw.modules.base.service.TBaParameterService;

/**
 * 参数配置Controller
 * @author handf
 * @version 2015-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaParameter")
public class TBaParameterController extends BaseController {

	@Autowired
	private TBaParameterService tBaParameterService;
	
	@ModelAttribute
	public TBaParameter get(@RequestParam(required=false) String id) {
		TBaParameter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaParameterService.get(id);
		}
		if (entity == null){
			entity = new TBaParameter();
		}
		return entity;
	}
	
	@RequiresPermissions("base:tBaParameter:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBaParameter tBaParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBaParameter> page = tBaParameterService.findPage(new Page<TBaParameter>(request, response), tBaParameter); 
		model.addAttribute("page", page);
		return "modules/base/tBaParameterList";
	}

	@RequiresPermissions("base:tBaParameter:view")
	@RequestMapping(value = "form")
	public String form(TBaParameter tBaParameter, Model model) {
		model.addAttribute("tBaParameter", tBaParameter);
		return "modules/base/tBaParameterForm";
	}

	@RequiresPermissions("base:tBaParameter:edit")
	@RequestMapping(value = "save")
	public String save(TBaParameter tBaParameter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBaParameter)){
			return form(tBaParameter, model);
		}
		tBaParameterService.save(tBaParameter);
		addMessage(redirectAttributes, "保存参数成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaParameter/?repage";
	}
	
	@RequiresPermissions("base:tBaParameter:edit")
	@RequestMapping(value = "delete")
	public String delete(TBaParameter tBaParameter, RedirectAttributes redirectAttributes) {
	    tBaParameterService.delete(tBaParameter);
		addMessage(redirectAttributes, "删除参数成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaParameter/?repage";
	}

}