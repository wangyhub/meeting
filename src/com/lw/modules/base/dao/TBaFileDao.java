/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaFile;

/**
 * 附件管理DAO接口
 * @author handf
 * @version 2015-08-10
 */
@MyBatisDao
public interface TBaFileDao extends CrudDao<TBaFile> {
	public List<TBaFile> queryFile(Map<String, List<String>> condition);
}