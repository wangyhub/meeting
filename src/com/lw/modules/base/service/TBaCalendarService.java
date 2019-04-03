/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.service.CrudService;
import com.lw.modules.base.entity.TBaCalendar;
import com.lw.modules.base.entity.TBaCalendarDay;
import com.lw.modules.base.dao.TBaCalendarDao;

/**
 * 日历设置Service
 * @author zhangxudong
 * @version 2015-12-03
 */
@Service
@Transactional(readOnly = true)
public class TBaCalendarService extends CrudService<TBaCalendarDao, TBaCalendarDay> {

	public TBaCalendar getTBaCalendar(String id) {
		TBaCalendarDay tbaCalendarDay = new TBaCalendarDay();
		tbaCalendarDay.setYear(id);
		List<TBaCalendarDay> tbaCalendarDayList = dao.findList(tbaCalendarDay);		
		TBaCalendar tBaCalendar = new TBaCalendar();
		tBaCalendar.setYear(id);
		String hdnwwdata1 = "";
		String hdnwwdata2 = "";
		String hdnwwdata3 = "";
		String hdnwwdata4 = "";
		String hdnwwdata5 = "";
		String hdnwwdata6 = "";
		String hdnwwdata7 = "";
		String hdnwwdata8 = "";
		String hdnwwdata9 = "";
		String hdnwwdata10 = "";
		String hdnwwdata11 = "";
		String hdnwwdata12 = "";
		//String year = "";
	    String month="";
	    String day="";
	    for(TBaCalendarDay tbaCalendarDay1 : tbaCalendarDayList){
	    	//year = tbaCalendarDay1.getWorkDate().substring(0, 4);
	    	month = tbaCalendarDay1.getWorkDate().substring(4, 6);
	    	day = tbaCalendarDay1.getWorkDate().substring(6, 8);
	    	day = Integer.parseInt(day) + "";
	    	month = Integer.parseInt(month) + "";
	    	if(month.equals("1")){	    		
	    		if("".equals(hdnwwdata1)){
	    	        hdnwwdata1 = day;
	    		}else{
	    			hdnwwdata1 = hdnwwdata1+","+day;
	    		}
	    	}else if(month.equals("2")){
	    		if("".equals(hdnwwdata2)){
	    	        hdnwwdata2 = day;
	    		}else{
	    			hdnwwdata2 = hdnwwdata2+","+day;
	    		}
	    	}else if(month.equals("3")){
	    		if("".equals(hdnwwdata3)){
	    	        hdnwwdata3 = day;
	    		}else{
	    			hdnwwdata3 = hdnwwdata3+","+day;
	    		}
	    	}else if(month.equals("4")){
	    		if("".equals(hdnwwdata4)){
	    	        hdnwwdata4 = day;
	    		}else{
	    			hdnwwdata4 = hdnwwdata4+","+day;
	    		}
	    	}else if(month.equals("5")){
	    		if("".equals(hdnwwdata5)){
	    	        hdnwwdata5 = day;
	    		}else{
	    			hdnwwdata5 = hdnwwdata5+","+day;
	    		}
	    	}else if(month.equals("6")){
	    		if("".equals(hdnwwdata6)){
	    	        hdnwwdata6 = day;
	    		}else{
	    			hdnwwdata6 = hdnwwdata6+","+day;
	    		}
	    	}else if(month.equals("7")){
	    		if("".equals(hdnwwdata7)){
	    	        hdnwwdata7 = day;
	    		}else{
	    			hdnwwdata7 = hdnwwdata7+","+day;
	    		}
	    	}else if(month.equals("8")){
	    		if("".equals(hdnwwdata8)){
	    	        hdnwwdata8 = day;
	    		}else{
	    			hdnwwdata8 = hdnwwdata8+","+day;
	    		}
	    	}else if(month.equals("9")){
	    		if("".equals(hdnwwdata9)){
	    	        hdnwwdata9 = day;
	    		}else{
	    			hdnwwdata9 = hdnwwdata9+","+day;
	    		}
	    	}else if(month.equals("10")){
	    		if("".equals(hdnwwdata10)){
	    	        hdnwwdata10 = day;
	    		}else{
	    			hdnwwdata10 = hdnwwdata10+","+day;
	    		}
	    	}else if(month.equals("11")){
	    		if("".equals(hdnwwdata11)){
	    	        hdnwwdata11 = day;
	    		}else{
	    			hdnwwdata11 = hdnwwdata11+","+day;
	    		}
	    	}else if(month.equals("12")){
	    		if("".equals(hdnwwdata12)){
	    	        hdnwwdata12 = day;
	    		}else{
	    			hdnwwdata12 = hdnwwdata12+","+day;
	    		}
	    	}
	    }
	    tBaCalendar.setHdnwwdata1(hdnwwdata1);
	    tBaCalendar.setHdnwwdata2(hdnwwdata2);
	    tBaCalendar.setHdnwwdata3(hdnwwdata3);
	    tBaCalendar.setHdnwwdata4(hdnwwdata4);
	    tBaCalendar.setHdnwwdata5(hdnwwdata5);
	    tBaCalendar.setHdnwwdata6(hdnwwdata6);
	    tBaCalendar.setHdnwwdata7(hdnwwdata7);
	    tBaCalendar.setHdnwwdata8(hdnwwdata8);
	    tBaCalendar.setHdnwwdata9(hdnwwdata9);
	    tBaCalendar.setHdnwwdata10(hdnwwdata10);
	    tBaCalendar.setHdnwwdata11(hdnwwdata11);
	    tBaCalendar.setHdnwwdata12(hdnwwdata12);
		return tBaCalendar;
	}		
	
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public void save(TBaCalendar tBaCalendar) {
		String year    = tBaCalendar.getYear();
		dao.delete(year);
	    saveTbaCalendar(tBaCalendar.getHdnwwdata1(),year,"01");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata2(),year,"02");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata3(),year,"03");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata4(),year,"04");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata5(),year,"05");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata6(),year,"06");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata7(),year,"07");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata8(),year,"08");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata9(),year,"09");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata10(),year,"10");
	    saveTbaCalendar(tBaCalendar.getHdnwwdata11(),year,"11");	
	    saveTbaCalendar(tBaCalendar.getHdnwwdata12(),year,"12");	
		
	}
	
	public void saveTbaCalendar(String daysStr,String year,String month){
		String[] days= daysStr.split(",");
		for(String day:days){
			TBaCalendarDay tbaCalendarDay = new TBaCalendarDay();
			if(Integer.parseInt(day)<10){
				day = "0"+Integer.parseInt(day);
			}
			tbaCalendarDay.preInsert();
			tbaCalendarDay.setYear(year);
			tbaCalendarDay.setWorkDate(year+month+day);
			dao.insert(tbaCalendarDay);
		}
	}
	
	
}