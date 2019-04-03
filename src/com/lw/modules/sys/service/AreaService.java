/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lw.modules.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.service.TreeService;
import com.lw.modules.sys.dao.AreaDao;
import com.lw.modules.sys.entity.Area;
import com.lw.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	/**
	 * 查找省份市区县
	 * 
	 * @param condition
	 * @return
	 */
	public List<Area> getAreaListByCondition(Map<String, Object> condition){
		return dao.getAreaListByCondition(condition);
	}
	
	public Area getParentArea(Map<String, Object> condition){
		return dao.getParentArea(condition);
	}
	
	/**
	 *根据最终地区获取完整的地区文本 
	 * 
	 */
	
	public  String getWholeAreaText(String id){
		String address="";
		Area a=dao.get(id);
		Map<String,Object> condition=new HashMap<String,Object>();
		if(a!=null){
			while(true){
				
				address=a.getName()+"-"+address;
				
				condition.put("cid", a.getId());
				a=dao.getParentArea(condition);
				if("1".equals(a.getCode())){
					break;
				}
			}
		}

		return address;
	}
	
	/**
	 * 展示地址详细信息
	 * @param addressCode
	 * @return
	 */
	public String showAddressDetail(String addressCode){
	    return dao.showAddressDetail(addressCode);
	}
	
}
