/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import java.util.List;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaActivity;
/**
 * 流程实例DAO接口
 * @author zhangxudong
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaActivityDao extends CrudDao<TBaActivity> {
	
    /**
     * 查询流程模板下的流程集合
     * @param tBaActivity
     * @return
     */
    public List<TBaActivity> countTBaActivity(TBaActivity tBaActivity);
    
    /**
     * 根据申请ID查询出流程
     * @param tBaActivity
     * @return
     */
    public TBaActivity getByTBaActivity(TBaActivity tBaActivity);
    
    /**
     * 根据证书编号查询出流程集合
     * @param tBaActivity
     * @return
     */
    public List<TBaActivity> getByCertcode(TBaActivity tBaActivity);
    
    /**
     * 根据userId和orgType查询已归档的申请
     * @param tBaActivity
     * @return
     */
    public List<TBaActivity> getTBaActivityByCert(TBaActivity tBaActivity);
    
}