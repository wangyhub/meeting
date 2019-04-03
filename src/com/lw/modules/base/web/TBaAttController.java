/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

import java.io.IOException;

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
import com.lw.modules.base.entity.TBaAtt;
import com.lw.modules.base.service.TBaAttService;

/**
 * 附件模板配置Controller
 * @author handf
 * @version 2015-08-09
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaAtt")
public class TBaAttController extends BaseController {

	@Autowired
	private TBaAttService tBaAttService;
	
	@ModelAttribute
	public TBaAtt get(@RequestParam(required=false) String id) {
		TBaAtt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaAttService.get(id);
		}
		if (entity == null){
			entity = new TBaAtt();
		}
		return entity;
	}
	
	@RequiresPermissions("base:tBaAtt:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBaAtt tBaAtt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBaAtt> page = tBaAttService.findPage(new Page<TBaAtt>(request, response), tBaAtt); 
		model.addAttribute("page", page);
		return "modules/base/tBaAttList";
	}

	@RequiresPermissions("base:tBaAtt:view")
	@RequestMapping(value = "form")
	public String form(TBaAtt tBaAtt, Model model) {
		model.addAttribute("tBaAtt", tBaAtt);
		return "modules/base/tBaAttForm";
	}

	@RequiresPermissions("base:tBaAtt:edit")
	@RequestMapping(value = "save")
	public String save(TBaAtt tBaAtt, Model model, 
	        RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, tBaAtt)){
			return form(tBaAtt, model);
		}
		tBaAttService.save(tBaAtt, request);
		addMessage(redirectAttributes, "保存附件模板成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaAtt/?repage";
	}
	
	@RequiresPermissions("base:tBaAtt:edit")
	@RequestMapping(value = "delete")
	public String delete(TBaAtt tBaAtt, RedirectAttributes redirectAttributes) {
	    tBaAttService.delete(tBaAtt);
		addMessage(redirectAttributes, "删除附件模板成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaAtt/?repage";
	}
	
	@ResponseBody
    @RequestMapping(value = "deleteFile")
    public void deleteFile(HttpServletRequest request, HttpServletResponse response){
        String jsonString = "";
        try {
            tBaAttService.deleteFile(request);
            jsonString = "{success:true,flag:1}";
        }catch (Exception e) {
            jsonString = "{success:false,flag:2}";
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(jsonString);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}