/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.lw.modules.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.utils.UserUtils;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;


/**
 * 第三方登录Controller
 * @author liud
 * @version 2015-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/base/userlogin")
public class ThirdPartyLoginController extends BaseController {

	/**
	 * QQ联合登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return UserUtils.get(id);
		}else{
			return new User();
		}
	}
	
	
	/**
	 * 访问QQ登录首页
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "qqlogin")
	public void toQQlogin(HttpServletRequest request, HttpServletResponse response, Model model) { 
		try {
		    response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	/**
	 * QQ联合登录返回
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "qqloginReturn")
	public String doQQLogin( Model model,HttpServletRequest request,HttpServletResponse response,Model map) {
	    try {
		   	System.out.println("qqLogin_ return ***********");
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken   = null,
            openId  = null;
			@SuppressWarnings("unused")
			long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参?");
      } else {
          accessToken = accessTokenObj.getAccessToken();
          tokenExpireIn = accessTokenObj.getExpireIn();
          OpenID openIDObj =  new OpenID(accessToken);
          openId = openIDObj.getUserOpenID();
      	User user=new User();
		
		 UserInfo qzoneUserInfo = new UserInfo(accessToken, openId);
         UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
         map.addAttribute("user",user);
         map.addAttribute("nickname", userInfoBean.getNickname());
         map.addAttribute("headimgurl", userInfoBean.getAvatar().getAvatarURL100());
         map.addAttribute("type","QQ");
         map.addAttribute("loginMethod","qq");
      	}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//return "redirect:"+Global.getHomepagePath()+"/userlogin/loginModel";
		return "modules/business/userlogin/completeInfo";
	}
	
}
