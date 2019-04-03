/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.web;

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
import com.lw.modules.sys.service.SystemService;
import com.lw.modules.workflow.entity.TBaTempActivity;
import com.lw.modules.workflow.service.TBaTempActivityService;

/**
 * 流程管理Controller
 * @author handf
 * @version 2015-09-01
 */
@Controller
@RequestMapping(value = "${adminPath}/workflow/tBaTempActivity")
public class TBaTempActivityController extends BaseController {

	@Autowired
	private TBaTempActivityService tBaTempActivityService;
	
	@Autowired
    private SystemService systemService;
	
	@ModelAttribute
	public TBaTempActivity get(@RequestParam(required=false) String id) {
		TBaTempActivity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaTempActivityService.get(id);
		}
		if (entity == null){
			entity = new TBaTempActivity();
		}
		return entity;
	}
	
	@RequiresPermissions("workflow:tBaTempActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBaTempActivity tBaTempActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBaTempActivity> page = tBaTempActivityService.findPage(new Page<TBaTempActivity>(request, response), tBaTempActivity); 
		model.addAttribute("page", page);
		return "modules/workflow/tBaTempActivityList";
	}

	@RequiresPermissions("workflow:tBaTempActivity:view")
	@RequestMapping(value = "form")
	public String form(TBaTempActivity tBaTempActivity, Model model) {
		model.addAttribute("tBaTempActivity", tBaTempActivity);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/workflow/tBaTempActivityForm";
	}

	@RequiresPermissions("workflow:tBaTempActivity:edit")
	@RequestMapping(value = "save")
	public String save(TBaTempActivity tBaTempActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBaTempActivity)){
			return form(tBaTempActivity, model);
		}
		String result = tBaTempActivityService.saveFlag(tBaTempActivity); // 保存结果  "" 为正常保存； "xxxx"为某节点保存失败
		String urlReturn = "redirect:" + Global.getAdminPath() + "/workflow/tBaTempActivity/?repage";
		
		if("".equals(result)){
		    addMessage(redirectAttributes, "保存流程管理成功");
		}else{
		    addMessage(redirectAttributes, "保存失败！" + result + "结点下，已有数据不能删除");
		    urlReturn = "redirect:" + Global.getAdminPath() + "/workflow/tBaTempActivity/form?id="
		         + tBaTempActivity.getId();
		}
		return urlReturn;
	}
	
	@RequiresPermissions("workflow:tBaTempActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(TBaTempActivity tBaTempActivity, Model model, RedirectAttributes redirectAttributes,
	        HttpServletRequest request) {
		String result = tBaTempActivityService.deleteFlag(tBaTempActivity);
		if("0".equals(result)){// 删除结果   0：成功； 1：失败。
		    addMessage(redirectAttributes, "删除流程管理成功");
		}
        if("1".equals(result)){
            addMessage(redirectAttributes, "该流程已发起，删除失败");
        }
		return "redirect:"+Global.getAdminPath()+"/workflow/tBaTempActivity/?repage";
	}
	
	@RequiresPermissions("workflow:tBaTempActivity:edit")
	@ResponseBody
    @RequestMapping(value = "checkActivityName")
    public String checkActivityName(HttpServletRequest request){
        String result = tBaTempActivityService.checkActivityName(request);
        return result;
    }
	
	@RequiresPermissions("workflow:tBaTempActivity:edit")
	@ResponseBody
    @RequestMapping(value = "checkActivityLogo")
    public String checkActivtyLogo(HttpServletRequest request){
        String result = tBaTempActivityService.checkActivtyLogo(request);
        return result;
    }

}