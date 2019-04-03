/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.ema.jwsserver.sms.sendCode;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.entity.TBaRegistered;
import com.lw.modules.base.dao.TBaRegisteredDao;
import com.lw.modules.sys.entity.Office;
import com.lw.modules.sys.entity.Role;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.service.SystemService;
import com.lw.modules.sys.utils.UserUtils;

/**
 * 注册模块Service
 * @author handf
 * @version 2015-08-13
 */
@Service
@Transactional(readOnly = true)
public class TBaRegisteredService extends CrudService<TBaRegisteredDao, TBaRegistered> {

    @Autowired
    private SystemService systemService;
    
    HashMap<String, String> map = new HashMap<String, String>();
    
	public TBaRegistered get(String id) {
		return super.get(id);
	}
	
	@SuppressWarnings("static-access")
	@Transactional(readOnly = false)
	public void save(TBaRegistered tBaRegistered, HttpServletRequest request) {
	    String username = request.getParameter("username"); // 机构账号
        String password = request.getParameter("password"); // 密码
        User user = new User();
        String mobile = tBaRegistered.getPhone(); // 手机号码
        user.setUserType("2");      // 机构注册
        user.setMobile(mobile);     // 固定电话
        user.setLoginName(username);// 登录名
        user.setPassword(systemService.entryptPassword(password));// 密码
        user.setName(tBaRegistered.getName());// 名称
        List<Role> roleList = new ArrayList<Role>();
        Role role = new Role();
        role.setId("6");
        roleList.add(role);
        user.setRoleList(roleList);// 设置机构角色
        Office office = new Office();
        office.setId("1");
        user.setOffice(office);
        user.setCompany(office);
        systemService.saveUser(user);
        // 保存成功之后返回ID
        String userid = user.getId();
        tBaRegistered.setUserId(userid);
        map.remove(mobile);
        request.getSession().setAttribute("suc", "机构信息注册成功");
	    super.save(tBaRegistered);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaRegistered tBaRegistered) {
		super.delete(tBaRegistered);
	}
	
	@Transactional(readOnly = false)
	public String checkName(String name) {
	    String result = null;// true: 不存在,false: 已存在
	    Map<String, Object> condition = new HashMap<String, Object>();
	    if(StringUtils.isNotEmpty(name)) {
	        condition.put("name", name);
	        List<TBaRegistered> user = dao.checkName(condition);
	        if(null != user && 0 < user.size()) {
	            result = "false";// 已存在
	        }else{
	            result = "true";// 不存在，可以注册
	        }
	    }
	    return result;
	}
	
	@Transactional(readOnly = false)
    public String checkUserName(HttpServletRequest request) {
        String result = null;// true: 不存在,false: 已存在
        String username = request.getParameter("username"); // 输入的用户名
        String flag = request.getParameter("flag");         // flag：1 要求用户名存在；0：要求用户名不存在
        Map<String, Object> condition = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(username)) {
            condition.put("loginName", username);
            List<User> user =  systemService.getUserAndId(condition);
            if(null != user && 0 < user.size()) {
                result = "false";// 已存在
            }else{
                result = "true";// 不存在，可以注册
            }
        }
        if(StringUtils.isNotEmpty(flag) && "1".equals(flag) ){
            if(result == "false"){
                result = "true";
            }else{
                result = "false";
            }
        }
        return result;
    }
	
	@Transactional(readOnly = false)
	public String checkOrgCode(String orgCode){
	    String result = null;// true: 不存在,false: 已存在
	    Map<String, Object> condition = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(orgCode)) {
            condition.put("orgCode", orgCode);
            List<TBaRegistered> user = dao.checkOrgCode(condition);
            if(null != user && 0 < user.size()) {
                result = "false";// 已存在
            }else{
                result = "true";// 不存在，可以注册
            }
        }
        return result;
    }
	
	@Transactional(readOnly = false)
    public int checkCode(HttpServletRequest request){
        int result = 0;// true: 不存在,false: 已存在
        String code = request.getParameter("code");
        String phone = request.getParameter("phone");
        if(StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(phone)){
            if(code.equals(map.get(phone))) {
                result = 0;
            }else {
                result = 1;
            }
        }else {
            result = 2;
        }
        return result;
    }
	
	@Transactional(readOnly = false)
    public int sendCode(HttpServletRequest request) {
        int i = 0;// 1:成功,0:失败
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        map.put(phone, code);
        sendCode sc = new sendCode();
        i = sc.sendSms(code, phone);
        UserUtils.clearCache();
        return i;
    }
	
	@Transactional(readOnly = false)
	public int checkUserAndMob(HttpServletRequest request) {
	    int result = 0;// 1:成功,0:失败
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(phone)){
            User user = new User();
            user.setLoginName(username);
            user.setMobile(phone);
            List<User> users =  systemService.checkUserAndMob(user);
            if(null != users && 0 < users.size()) {
                result = 1;// 已存在，可以发送验证码
            }else {
                result = 0;// 不存在
            }
        }else {
            result = 0;// 传值失败
        }
	    return result;
	}
	
	@SuppressWarnings("static-access")
	@Transactional(readOnly = false)
	public void getPwd(TBaRegistered tBaRegistered, Model model,
	        HttpServletRequest request, RedirectAttributes redirectAttributes) {
	    String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mobile = request.getParameter("phone");
        User user = new User();
        user.setMobile(mobile);     // 固定电话
        user.setLoginName(username);// 登录名
        user.setPassword(systemService.entryptPassword(password));// 密码
        UserUtils.clearCache();
        systemService.getPwd(user);
        map.remove(mobile);
	}
}