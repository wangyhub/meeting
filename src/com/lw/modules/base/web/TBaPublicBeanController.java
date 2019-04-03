/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.utils.IdGen;
import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.base.entity.TBaPublicBean;
import com.lw.modules.base.entity.TBaSonPublicBean;
import com.lw.modules.base.entity.TBaTableConfig;
import com.lw.modules.base.entity.TBaTableField;
import com.lw.modules.base.service.TBaPublicBeanService;
import com.lw.modules.base.service.TBaSonPublicBeanService;
import com.lw.modules.base.service.TBaTableConfigService;


/**
 * 功能测试数据录入Controller
 * @author weixm
 * @version 2016-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaPublicBean")
public class TBaPublicBeanController extends BaseController {
	
	@Autowired
	private TBaPublicBeanService tBaPublicBeanService;
	@Autowired
	private TBaSonPublicBeanService tBaSonPublicBeanService; 
	@Autowired
	private TBaTableConfigService tBaTableConfigService;
	
//	@ModelAttribute
//	public TBaPublicBean get(@RequestParam(required=false) String id) {
//		TBaPublicBean entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = tBaPublicBeanService.get(id);
//		}
//		if (entity == null){
//			entity = new TBaPublicBean();
//		}
//		return entity;
//	}
	
	@RequiresPermissions("base:tBaTableConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(String tableConfigId, TBaPublicBean tBaPublicBean, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {		
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(tableConfigId);
		List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
		List<TBaTableField> findDataList = tBaTableConfigService.getFindData(tableConfigId);
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 0;i < findDataList.size();i++){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if("01".equals(findDataList.get(i).getFieldType())||"02".equals(findDataList.get(i).getFieldType())||"03".equals(findDataList.get(i).getFieldType())){
				if("01".equals(findDataList.get(i).getQueryType())||"02".equals(findDataList.get(i).getQueryType())){
					map.put(findDataList.get(i).getFieldCode(), request.getParameter(findDataList.get(i).getFieldCode()));
				}else if("03".equals(findDataList.get(i).getQueryType())){
					String start = request.getParameter(findDataList.get(i).getFieldCode()+"_start");
					String end = request.getParameter(findDataList.get(i).getFieldCode()+"_end");
					if(StringUtils.isNotEmpty(start)){
						map.put(findDataList.get(i).getFieldCode()+"_start", start);
					}else{
						map.put(findDataList.get(i).getFieldCode()+"_start", null);
					}
					if(StringUtils.isNotEmpty(end)){
						map.put(findDataList.get(i).getFieldCode()+"_end", end);
					}else{
						map.put(findDataList.get(i).getFieldCode()+"_end", null);
					}
				}
			}else if("04".equals(findDataList.get(i).getFieldType())){
				String start = request.getParameter(findDataList.get(i).getFieldCode()+"_start");
				String end = request.getParameter(findDataList.get(i).getFieldCode()+"_end");
				if(StringUtils.isNotEmpty(start)){
					map.put(findDataList.get(i).getFieldCode()+"_start", sdf.parse(start));
				}else{
					map.put(findDataList.get(i).getFieldCode()+"_start", null);
				}
				if(StringUtils.isNotEmpty(end)){
					map.put(findDataList.get(i).getFieldCode()+"_end", sdf.parse(end));
				}else{
					map.put(findDataList.get(i).getFieldCode()+"_end", null);
				}
			}
		}
		//拼接查询条件
		//2、获取显示列表标题名称
		//3、获取展示字段名称
		Page<TBaPublicBean> page = tBaPublicBeanService.findPage(new Page<TBaPublicBean>(request, response), tBaPublicBean , tableConfigId, map); 
		model.addAttribute("map", map);
		model.addAttribute("condList", tBaPublicBean.getList());
		model.addAttribute("findDataList", findDataList);
		model.addAttribute("page", page);
		model.addAttribute("tBaTableFieldList", list);
		model.addAttribute("tableConfigId", tableConfigId);
		return "modules/base/tBaTableFunctionTestList";
	}

	@RequiresPermissions("base:tBaTableConfig:view")
	@RequestMapping(value = "form")
	public String form(TBaPublicBean tBaPublicBean, String tableConfigId, String tableId, HttpServletRequest request, HttpServletResponse response, Model model) {
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(tableConfigId);
		List<TBaTableConfig> sonTableInfoList = tBaTableConfigService.findSonTableInfoByTableName(tBaTableConfig.getTableName());
		TBaTableConfig sonTBaTableConfig = null;
		TBaPublicBean sonTBaPublicBean = new TBaPublicBean();
		if(sonTableInfoList.size() != 0){
			for(int i = 0; i < sonTableInfoList.size(); i++){
				sonTBaTableConfig = sonTableInfoList.get(i);
			}
			tBaPublicBeanService.findPage(new Page<TBaPublicBean>(request, response), sonTBaPublicBean , sonTBaTableConfig.getId(), null);
			JSONArray jsona = JSONArray.fromObject(sonTBaPublicBean.getList());
			model.addAttribute("sonCondList", jsona.toString());
			model.addAttribute("sonconList", sonTBaPublicBean.getList());
			List<TBaSonPublicBean> sonList = tBaSonPublicBeanService.doSql(sonTBaTableConfig, tableId);
			model.addAttribute("sonListSize", sonList.size());
			model.addAttribute("sonList", sonList);
		}
		List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
		tBaPublicBeanService.findPage(new Page<TBaPublicBean>(request, response), tBaPublicBean , tableConfigId, null);
		model.addAttribute("condList", tBaPublicBean.getList());
		tBaPublicBean = tBaPublicBeanService.doSql(tBaTableConfig, tableId);
		//得到该主表在子表中的信息
//		JsonConfig jc = new JsonConfig();
//		jc.setExcludes(new String[]{"global","isNewRecord","delFlag","dbName","office","parent","officeList","html","createBy","createDate","updateDate","updateBy","page","sqlMap","currentUser","roleIdList","roleList","list","placeId","applyId"});
//		jc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		JSONArray json = JSONArray.fromObject(sonList, jc);
//		model.addAttribute("sonLista", json.toString());
		model.addAttribute("sonTableInfoList", sonTableInfoList);
		model.addAttribute("tBaTableFieldList", list);
		model.addAttribute("tableConfigId", tableConfigId);
		model.addAttribute("tBaPublicBean", tBaPublicBean);
		return "modules/base/tBaTableInputDataList";
	}

	@RequiresPermissions("base:tBaTableConfig:edit")
	@RequestMapping(value = "save")
	public String save(TBaPublicBean tBaPublicBean, String sonTableId, String tableConfigId, Model model, RedirectAttributes redirectAttributes) {
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(tableConfigId);
		if(StringUtils.isEmpty(tBaPublicBean.getStr1())){
			tBaPublicBean.setStr1(IdGen.uuid());
		}
		tBaPublicBeanService.save(tBaPublicBean,tBaTableConfig);
		if(StringUtils.isNotEmpty(sonTableId)){
			TBaTableConfig sonTBaTableConfig = tBaTableConfigService.get(sonTableId);
			List<TBaSonPublicBean> tbaBaSonPublicBeanList = tBaPublicBean.getTbaBaSonPublicBeanList();
			for(int i = 0;i < tbaBaSonPublicBeanList.size();i++){
				tbaBaSonPublicBeanList.get(i).setStr2(tBaPublicBean.getStr1());
			}
			tBaSonPublicBeanService.save(tbaBaSonPublicBeanList, sonTBaTableConfig);
		}
		addMessage(redirectAttributes, "保存表单记录成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaPublicBean/?tableConfigId="+tableConfigId;
	}
	
	@RequiresPermissions("base:tBaTableConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(TBaPublicBean tBaPublicBean, String tableConfigId, RedirectAttributes redirectAttributes) {
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(tableConfigId);
		tBaPublicBeanService.delete(tBaPublicBean,tBaTableConfig);
		addMessage(redirectAttributes, "删除表单记录成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaPublicBean/?tableConfigId="+tableConfigId;
	}
	
	@RequestMapping(value = "ajaxDeleteSonTable")
	public String ajaxDeleteSonTable(String id,String configTableId){
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(configTableId);
		tBaSonPublicBeanService.delete(id, tBaTableConfig);
		return "删除成功";
	}



}