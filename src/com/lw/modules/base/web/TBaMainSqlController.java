/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lw.modules.base.entity.TBaMainSql;
import com.lw.modules.base.service.TBaMainSqlService;

/**
 * 动态查询Controller
 * @author handf 
 * @version 2016-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaMainSql")
public class TBaMainSqlController extends BaseController {

	@Autowired
	private TBaMainSqlService tBaMainSqlService;
	
	@ModelAttribute
	public TBaMainSql get(@RequestParam(required=false) String id) {
		TBaMainSql entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaMainSqlService.get(id);
		}
		if (entity == null){
			entity = new TBaMainSql();
		}
		return entity;
	}
	
	/**
	 * 动态查询信息集合
	 * @param tBaMainSql
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list(TBaMainSql tBaMainSql, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBaMainSql> page = tBaMainSqlService.findPage(new Page<TBaMainSql>(request, response), tBaMainSql); 
		model.addAttribute("page", page);
		return "modules/base/tBaMainSqlList";
	}
	
	/**
	 * 删除记录
	 * @param tBaMainSql
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(TBaMainSql tBaMainSql, RedirectAttributes redirectAttributes) {
		tBaMainSqlService.delete(tBaMainSql);
		addMessage(redirectAttributes, "删除动态查询成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaMainSql/?repage";
	}
	
	/**
	 * 配置信息页面
	 * @param tBaMainSql
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "mainSqlform")
	public String mainSqlform(TBaMainSql tBaMainSql, Model model) {
		model.addAttribute("tBaMainSql", tBaMainSql);
		return "modules/base/tBaMainSqlForm";
	}
	
	/**
	 * 保存配置信息
	 * @param tBaMainSql
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveMainSql")
	public String saveMainSql(TBaMainSql tBaMainSql, Model model, RedirectAttributes redirectAttributes) {
		tBaMainSqlService.save(tBaMainSql);
		return "redirect:"+Global.getAdminPath()+"/base/tBaMainSql/?repage";
	}
	
	/**
	 * 检测sql语句是否可执行
	 * @param headSql
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkIsSql")
	public Map<String, String> checkIsSql(String headSql){
		Map<String, String> map = tBaMainSqlService.checkIsSql(headSql);//返回执行查询结果的条数
	    return map;
	}
}