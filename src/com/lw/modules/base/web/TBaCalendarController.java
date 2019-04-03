/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.web.BaseController;
import com.lw.modules.base.entity.TBaCalendar;
import com.lw.modules.base.service.TBaCalendarService;

/**
 * 日历设置Controller
 * @author zhangxudong
 * @version 2015-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaCalendar")
public class TBaCalendarController extends BaseController {

	@Autowired
	private TBaCalendarService tBaCalendarService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "form")
	public String form(TBaCalendar tBaCalendar, Model model) {
		List list=new ArrayList();	
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);
		for(int i=0;i<5;i++){
			Map map = new HashMap();
			map.put("year",(year+1-i));
			list.add(map);
		}
		if(tBaCalendar.getYear() ==null || "".equals(tBaCalendar.getYear())){
			tBaCalendar.setYear(year+"");
		}else{
			year = Integer.parseInt(tBaCalendar.getYear().toString());
		}
		tBaCalendar = tBaCalendarService.getTBaCalendar(year+"");
		if(tBaCalendar==null){
			tBaCalendar = new TBaCalendar();
			tBaCalendar.setYear(year+"");
		}
		model.addAttribute("yearList",list);
		model.addAttribute("tBaCalendar", tBaCalendar);
		return "modules/base/tBaCalendarForm";
	}

	@RequestMapping(value = "save")
	public String save(TBaCalendar tBaCalendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBaCalendar)){
			return form(tBaCalendar, model);
		}
		tBaCalendarService.save(tBaCalendar);
		addMessage(redirectAttributes, "保存日历设置成功");
		return "redirect:"+Global.getAdminPath()+"/base/tBaCalendar/form?year="+tBaCalendar.getYear().toString();
	}
	

}