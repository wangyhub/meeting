/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import java.util.List;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaTask;

/**
 * 任务记录DAO接口
 * @author 张旭东
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaTaskDao extends CrudDao<TBaTask> {
	
	public TBaTask getTBaTask(TBaTask tBaTask);
    
    public void deleteOthersTask(TBaTask tBaTask);
    
    public List<TBaTask> getTBaTaskList(TBaTask tBaTask);
    
    public int updateFlag(TBaTask tBaTask);
    
    public int deleteTask(TBaTask tBaTask);
    
    /**
     * 得到上一结点处理的结果
     * @param tBaTask
     * @return
     */
    public TBaTask getLastDual(TBaTask tBaTask);
}