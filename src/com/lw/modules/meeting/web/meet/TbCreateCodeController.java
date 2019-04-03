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
import com.lw.modules.meeting.entity.meet.TbCreateCode;
import com.lw.modules.meeting.service.meet.TbCreateCodeService;

import java.util.List;

/**
 * 会议创建码Controller
 * @author meijx
 * @version 2019-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meet/tbCreateCode")
public class TbCreateCodeController extends BaseController {

	@Autowired
	private TbCreateCodeService tbCreateCodeService;
	
	@ModelAttribute
	public TbCreateCode get(@RequestParam(required=false) String id) {
		TbCreateCode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbCreateCodeService.get(id);
		}
		if (entity == null){
			entity = new TbCreateCode();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TbCreateCode tbCreateCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbCreateCode> page = tbCreateCodeService.findPage(new Page<TbCreateCode>(request, response), tbCreateCode); 
		model.addAttribute("page", page);
		return "modules/meeting/meet/tbCreateCodeList";
	}

	@RequestMapping(value = "form")
	public String form(TbCreateCode tbCreateCode, Model model) {
		model.addAttribute("tbCreateCode", tbCreateCode);
		return "modules/meeting/meet/tbCreateCodeForm";
	}

	@RequestMapping(value = "save")
	public String save(TbCreateCode tbCreateCode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbCreateCode)){
			return form(tbCreateCode, model);
		}
		tbCreateCodeService.save(tbCreateCode);
		addMessage(redirectAttributes, "保存会议创建码成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbCreateCode/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TbCreateCode tbCreateCode, RedirectAttributes redirectAttributes) {
		tbCreateCodeService.delete(tbCreateCode);
		addMessage(redirectAttributes, "删除会议创建码成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meet/tbCreateCode/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "checkCode")
	public String checkCode(HttpServletRequest request) {
		String createCode = request.getParameter("createCode");
		TbCreateCode cond = new TbCreateCode();
		cond.setCreateCode(createCode);
		cond.setStatus("0");
		List<TbCreateCode> list = tbCreateCodeService.findList(cond);
		if(list==null || list.size()==0){
			return "0";
		}
		else{
			return "1";
		}
	}

}