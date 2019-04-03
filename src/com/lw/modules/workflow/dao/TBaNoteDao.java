/**
 * Copyright &copy; 2012-2014 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import java.util.List;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaNote;

/**
 * 流程实例DAO接口
 * @author zhangxudong
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaNoteDao extends CrudDao<TBaNote> {
	
    public TBaNote getTBaNote(TBaNote tBaNote);
    
    public List<TBaNote> countTBaNote(TBaNote tBaNote);
    
    public List<TBaNote> getTBaNoteList(TBaNote tBaNote);
    
    public List<TBaNote> getBeforeTBaNote(TBaNote tBaNote);
    
    public int updateFlag(TBaNote tBaNote);
}