/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaPublicBean;

/**
 * 功能测试数据录入DAO接口
 * @author weixm
 * @version 2016-04-15
 */
@MyBatisDao
public interface TBaPublicBeanDao extends CrudDao<TBaPublicBean> {

	public List<TBaPublicBean> findList(Map<String, Object> map);
	/*
	 * 执行sql语句，返回list
	 */
	public List<TBaPublicBean> exeSql(Map<String, Object> map);
	
	/*
	 * 执行sql语句，返回对象数据
	 */
	public TBaPublicBean doSql(Map<String, Object> map);
	
	public void save(Map<String, Object> map);
	
	public void delete(Map<String, Object> map);
	
}