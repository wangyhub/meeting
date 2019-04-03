/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.web.BaseController;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.entity.TBaRegistered;
import com.lw.modules.base.service.TBaRegisteredService;

/**
 * 注册模块Controller
 * @author handf
 * @version 2015-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaRegistered")
public class TBaRegisteredController extends BaseController {

	@Autowired
	private TBaRegisteredService tBaRegisteredService;

	@ModelAttribute
	public TBaRegistered get(@RequestParam(required=false) String id) {
		TBaRegistered entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaRegisteredService.get(id);
		}
		if (entity == null){
			entity = new TBaRegistered();
		}
		return entity;
	}

	@RequestMapping(value = "form")
	public String form(TBaRegistered tBaRegistered, Model model) {
	    model.addAttribute("tBaRegistered", tBaRegistered);
		return "modules/base/tBaRegisteredForm";
	}


	@RequestMapping(value = "save")
	public String save(TBaRegistered tBaRegistered, Model model, RedirectAttributes redirectAttributes,
	        HttpServletRequest request) {
		if (!beanValidator(model, tBaRegistered)){
			return form(tBaRegistered, model);
		}
		tBaRegisteredService.save(tBaRegistered, request);
		return "redirect:" + Global.getAdminPath();
	}
	
	@RequestMapping(value = "delete")
	public String delete(TBaRegistered tBaRegistered, RedirectAttributes redirectAttributes) {
		tBaRegisteredService.delete(tBaRegistered);
		addMessage(redirectAttributes, "删除注册成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaRegistered/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String name){
	    String result = tBaRegisteredService.checkName(name);
	    return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkOrgCode")
	public String checkOrgCode(String orgCode){ 
	    String result = tBaRegisteredService.checkOrgCode(orgCode);
        return result;
	}
	
	@ResponseBody
    @RequestMapping(value = "checkUserName")
    public String checkUserName(HttpServletRequest request){
        String result = tBaRegisteredService.checkUserName(request);
        return result;
    }
	
	@ResponseBody
    @RequestMapping(value = "checkCode")
    public int checkCode(HttpServletRequest request){
        int result = tBaRegisteredService.checkCode(request);
        return result;
    }
	
    /**
     * 发送验证码
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sendCode")
    public int sendCode(HttpServletRequest request) {
        return tBaRegisteredService.sendCode(request);
    }

    @RequestMapping(value = "forgetPwd")
    public String forgetPwd(TBaRegistered tBaRegistered, Model model)
    {
        model.addAttribute("tBaRegistered", tBaRegistered);
        return "modules/base/forgetPwd";
    }

    /**
     * 校验用户名和手机号是否匹配
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkUserAndMob")
    public int checkUserAndMob(HttpServletRequest request) {
        return tBaRegisteredService.checkUserAndMob(request);
    }
	
    @RequestMapping(value = "getPwd")
    public String getPwd(TBaRegistered tBaRegistered, Model model,
        HttpServletRequest request, RedirectAttributes redirectAttributes) {
        tBaRegisteredService.getPwd(tBaRegistered, model, request,redirectAttributes);
        request.getSession().setAttribute("suc", "成功找回密码");
        return "redirect:" + Global.getAdminPath();
    }
}