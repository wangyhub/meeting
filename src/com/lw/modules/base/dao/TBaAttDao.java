/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaAtt;

/**
 * 附近配置DAO接口
 * @author handf
 * @version 2015-08-09
 */
@MyBatisDao
public interface TBaAttDao extends CrudDao<TBaAtt> {
	
    public void deleteFile(TBaAtt tBaAtt);
}