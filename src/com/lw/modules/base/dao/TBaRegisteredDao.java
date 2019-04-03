/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaRegistered;

/**
 * 注册模块DAO接口
 * @author handf
 * @version 2015-08-13
 */
@MyBatisDao
public interface TBaRegisteredDao extends CrudDao<TBaRegistered> {
	
    public List<TBaRegistered> checkName(Map<String, Object> condition);
    
    public List<TBaRegistered> checkOrgCode(Map<String, Object> condition);
    
}