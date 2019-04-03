/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lw.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.lw.common.persistence.TreeDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	/**
	 * 查找省份市区县
	 * @param condition
	 * @return
	 */
	public List<Area> getAreaListByCondition(Map<String, Object> condition);
	
	public Area getParentArea(Map<String, Object> condition);
	
	public String showAddressDetail(String addressCode);
}
