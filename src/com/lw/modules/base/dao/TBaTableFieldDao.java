/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaTableConfig;
import com.lw.modules.base.entity.TBaTableField;

/**
 * 表单配置DAO接口
 * @author weixm
 * @version 2016-03-29
 */
@MyBatisDao
public interface TBaTableFieldDao extends CrudDao<TBaTableField> {
	
	public void deleteByConfigId(TBaTableField tBaTableField);
	
	public void deleteTBaTableFieldById(TBaTableField tBaTableField);
	


	/**
	 * 获取数据库中表的列对象集合
	 * @param tableName
	 * @return
	 */
	public List<TBaTableField> findDataBaseTableField(String tableName);
	
	
	/**
	 * 获取定义表中定义的列的信息
	 */
	public List<TBaTableField> findDataFormTableField(TBaTableConfig tBaTableConfig);
	
	/**
	 * 查找列表显示查询的字段
	 */
	public List<TBaTableField> getFindData(Map<String, Object> map);
	/*
	 * 根据表id查找表中的字段
	 */
	public List<TBaTableField> getColumnByTableId(String tableId);
	/*
	 * 根据表id查找字段list
	 */
	public List<TBaTableField> getDataByTableName(String tableName);

}