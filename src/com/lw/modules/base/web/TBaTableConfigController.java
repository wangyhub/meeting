/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
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
import com.lw.modules.base.entity.TBaTableConfig;
import com.lw.modules.base.service.TBaTableConfigService;

/**
 * 表单配置Controller
 * @author weixm
 * @version 2016-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaTableConfig")
public class TBaTableConfigController extends BaseController {

	@Autowired
	private TBaTableConfigService tBaTableConfigService;
	
	@ModelAttribute
	public TBaTableConfig get(@RequestParam(required=false) String id) {
		TBaTableConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaTableConfigService.get(id);
		}
		if (entity == null){
			entity = new TBaTableConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("base:tBaTableConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBaTableConfig tBaTableConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBaTableConfig> page = tBaTableConfigService.findPage(new Page<TBaTableConfig>(request, response), tBaTableConfig); 
		model.addAttribute("page", page);
		return "modules/base/tBaTableConfigList";
	}

	@RequiresPermissions("base:tBaTableConfig:view")
	@RequestMapping(value = "form")
	public String form(TBaTableConfig tBaTableConfig, Model model) {
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(new String[]{"global","isNewRecord","delFlag","dbName","office","parent","officeList","html","createBy","createDate","updateDate","updateBy","page","sqlMap","currentUser","roleIdList","roleList","list","placeId","applyId"});
		jc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray json = JSONArray.fromObject(tBaTableConfig.getTBaTableFieldList(), jc);
		model.addAttribute("tBaTableFieldList", json.toString());
		model.addAttribute("tBaTableConfig", tBaTableConfig);
		return "modules/base/tBaTableConfigForm";
	}

	@RequiresPermissions("base:tBaTableConfig:edit")
	@RequestMapping(value = "save") 
	public String save(TBaTableConfig tBaTableConfig, Model model, RedirectAttributes redirectAttributes) {
		tBaTableConfig.setMatchDataBase("00");
		tBaTableConfigService.save(tBaTableConfig);
		addMessage(redirectAttributes, "保存表单配置成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaTableConfig/?repage";
	}
	
	@RequiresPermissions("base:tBaTableConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(TBaTableConfig tBaTableConfig, RedirectAttributes redirectAttributes) {
		tBaTableConfigService.delete(tBaTableConfig);
		addMessage(redirectAttributes, "删除表单配置成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaTableConfig/?repage";
	}
	
	
	
//	@RequestMapping(value = "inputData")
//	public String inputData(String id,HttpServletRequest request,HttpServletResponse response,Model model){
//		
//		return "modules/base/tBaTableInputDataList";
//	}
	
	@ResponseBody
	@RequestMapping(value="ajaxMatchDataBase")
	public String ajaxMatchDataBase(String id,String synMethod,HttpServletRequest request,HttpServletResponse response){
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(id);
		String result = "同步数据库成功";
		try{
		    tBaTableConfigService.dbSynch(tBaTableConfig, synMethod);
		}catch (BadSqlGrammarException e) {
			tBaTableConfig.setMatchDataBase("00");
			tBaTableConfigService.save(tBaTableConfig);
			result = "同步数据库失败，Integer字段类型的数字精度说明符超出范围 (1 到 38)";
		}catch (SQLException e) {
			tBaTableConfig.setMatchDataBase("00");
			tBaTableConfigService.save(tBaTableConfig);
			result = "同步数据库出现异常";
		}catch (Exception e) {
			tBaTableConfig.setMatchDataBase("00");
			tBaTableConfigService.save(tBaTableConfig);
			result = "系统出现异常";
		}
		return result;
	}
	
//	@ResponseBody
//	@RequestMapping(value="ajaxCancelDataBase")
//	public String ajaxCancelDataBase(String id,HttpServletRequest request,HttpServletResponse response){
//		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(id);
//		tBaTableConfig.setMatchDataBase("00");
//		tBaTableConfigService.save(tBaTableConfig);
//		return "取消同步数据库成功";
//	}
	
	/*
	 * jsp页面删除操作
	 */
	@ResponseBody
	@RequestMapping(value="ajaxDeleteTBaTableField")
	public String ajaxDeleteTBaTableField(String fieldId,HttpServletRequest request,HttpServletResponse response){
		tBaTableConfigService.deleteTBaTableFieldById(fieldId);
		return "删除成功";
	}
	
	/*
	 * jsp页面验证tableName是否重名
	 */
	@ResponseBody
	@RequestMapping(value="ajaxCheckTableName")
	public Integer ajaxCheckTableName(String tableName,HttpServletRequest request,HttpServletResponse response){
		Integer count = tBaTableConfigService.checkTableName(tableName);
		return count;
	}
	
	

}