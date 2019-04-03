/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import java.util.List;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaTempActivity;

/**
 * 流程管理DAO接口
 * @author handf
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaTempActivityDao extends CrudDao<TBaTempActivity> {
	
    public List<TBaTempActivity> checkActivtyName(TBaTempActivity tBaTempActivit);
    
    public List<TBaTempActivity> checkActivtyLogo(TBaTempActivity tBaTempActivity);
    
    public TBaTempActivity getByTBaTempActivity(TBaTempActivity tBaTempActivity);

}