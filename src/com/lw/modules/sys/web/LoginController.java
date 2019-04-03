/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lw.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lw.common.config.Global;
import com.lw.common.security.shiro.session.SessionDAO;
import com.lw.common.servlet.ValidateCodeServlet;
import com.lw.common.utils.CacheUtils;
import com.lw.common.utils.CookieUtils;
import com.lw.common.utils.IdGen;
import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.base.entity.TBaParameter;
import com.lw.modules.base.service.TBaParameterService;
import com.lw.modules.sys.entity.Dict;
import com.lw.modules.sys.entity.Office;
import com.lw.modules.sys.entity.Role;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.security.FormAuthenticationFilter;
import com.lw.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.lw.modules.sys.service.OfficeService;
import com.lw.modules.sys.service.SystemService;
import com.lw.modules.sys.utils.UserUtils;


/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
    private TBaParameterService tBaParameterService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

		// 获取首页参数
		TBaParameter tBaParameterPara = new TBaParameter();
        Dict dict = new Dict();
        dict.setValue("01");
        dict.setType("temp_name");
        tBaParameterPara.setParaType(dict);
        List<TBaParameter> indexParas = tBaParameterService.findList(tBaParameterPara);
        Principal principal = UserUtils.getPrincipal();
		
//		// 默认页签模式
//		String tabmode = CookieUtils.getCookie(request, "tabmode");
//		if (tabmode == null){
//			CookieUtils.setCookie(response, "tabmode", "1");
//		}
		
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + adminPath;
		}
//		String view;
//		view = "/WEB-INF/views/modules/sys/sysLogin.jsp";
//		view = "classpath:";
//		view += "jar:file:/D:/GitHub/jeesite/src/main/webapp/WEB-INF/lib/jeesite.jar!";
//		view += "/"+getClass().getName().replaceAll("\\.", "/").replace(getClass().getSimpleName(), "")+"view/sysLogin";
//		view += ".jsp";
		model.addAttribute("indexParas", indexParas);
		return "modules/sys/sysLogin";
	}
	
	@RequestMapping(value = "${adminPath}/register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
	    return "modules/sys/sysRegister";
	}
	
	@RequestMapping(value = "${adminPath}/register/doRegister")
    public @ResponseBody String doRegister(HttpServletRequest request, HttpServletResponse response) {
	   String name = request.getParameter("name");
	   String pass = request.getParameter("pass");
	   String phone = request.getParameter("phone");
//	   String verificationCode = request.getParameter("verificationCode");
	    if (systemService.getUserByLoginName(name) != null) {
	        return "exists";
        }
	    User user = new User();
	    Office office = officeService.get(Global.getConfig("ent_id"));
	    user.setCompany( office);
	    user.setOffice( office);
	    user.setLoginName(name);// 登录名
	    user.setName(name);// 姓名
	    user.setPassword(SystemService.entryptPassword(pass));// 密码
	    user.setPhone(phone);// 电话
	    user.setMobile(phone); // 手机
	    user.setLoginFlag("1"); // 是否允许登陆
	    user.setUserType(Global.getConfig("usertype_ent")); // 用户类型
	    Role role = systemService.getRole(Global.getConfig("ent_role_id"));
	    List<Role> list = Lists.newArrayList();
	    list.add(role);
	    user.setRoleList(list);
	    systemService.saveUser(user);
        return "success";
    }
        

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 获取首页参数
		TBaParameter tBaParameterPara = new TBaParameter();
        Dict dict = new Dict();
        dict.setValue("01");
        dict.setType("temp_name");
        tBaParameterPara.setParaType(dict);
        List<TBaParameter> indexParas = tBaParameterService.findList(tBaParameterPara);
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + adminPath;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		}
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		// 如果是手机登录，则返回JSON字符串
		if (mobile){
	        return renderString(response, model);
		}
		
		model.addAttribute("indexParas", indexParas);
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = UserUtils.getPrincipal();

		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(principal.getLoginName(), false, true);
		
		if (logger.isDebugEnabled()){
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)){
				CookieUtils.setCookie(response, "LOGINED", "true");
			}else if (StringUtils.equals(logined, "true")){
				UserUtils.getSubject().logout();
				return "redirect:" + adminPath + "/login";
			}
		}
		
		// 如果是手机登录，则返回JSON字符串
		if (principal.isMobileLogin()){
			if (request.getParameter("login") != null){
				return renderString(response, principal);
			}
			if (request.getParameter("index") != null){
				return "modules/sys/sysIndex";
			}
			return "redirect:" + adminPath + "/login";
		}
		
		if(UserUtils.getUser().isAdmin() || UserUtils.userHasRole("manager")){
			return "modules/sys/sysIndex";
		}
		
		//return "modules/sys/sysFrame";
		return "redirect:" + adminPath + "/meeting/meet/tbMeeting/";
	}
	
	@RequestMapping(value = "${adminPath}/login/frame")
	public String frame(HttpServletRequest request, HttpServletResponse response, Model model) {
		String frameType = request.getParameter("frameType");
		if(StringUtils.isNotEmpty(frameType)){
			return "modules/sys/sysIndex2";
		}
		
		return "modules/sys/sysFrame";
	}
	
	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
}
