/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.web.back;

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
import com.lw.modules.meeting.entity.back.TbBackstage;
import com.lw.modules.meeting.service.back.TbBackstageService;

/**
 * 数据维护Controller
 * @author meijx
 * @version 2019-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/back/tbBackstage")
public class TbBackstageController extends BaseController {

	@Autowired
	private TbBackstageService tbBackstageService;
	
	@ModelAttribute
	public TbBackstage get(@RequestParam(required=false) String id) {
		TbBackstage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbBackstageService.get(id);
		}
		if (entity == null){
			entity = new TbBackstage();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbBackstage tbBackstage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbBackstage> page = tbBackstageService.findPage(new Page<TbBackstage>(request, response), tbBackstage);
		model.addAttribute("page", page);
		return "modules/meeting/back/tbBackstageList";
	}

	@RequestMapping(value = "form")
	public String form(TbBackstage tbBackstage, Model model) {
		model.addAttribute("tbBackstage", tbBackstage);
		return "modules/meeting/back/tbBackstageForm";
	}

	@RequestMapping(value = "view")
	public String view(TbBackstage tbBackstage, Model model) {
		model.addAttribute("tbBackstage", tbBackstage);
		return "modules/meeting/back/tbBackstageFormView";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public TbBackstage save(TbBackstage tbBackstage, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		String commit = request.getParameter("commit");
		TbBackstage returnInfo = tbBackstageService.runSql(tbBackstage.getExecSql(),commit);
		if("1".equals(commit) && returnInfo.getRemarks()==null) {
			TbBackstage stage = tbBackstageService.analyzeSql(tbBackstage.getExecSql());
			if (stage != null) {
				stage.setAffectNum(returnInfo.getAffectNum());
				tbBackstageService.save(stage);
			}
		}

		return returnInfo;
	}

}