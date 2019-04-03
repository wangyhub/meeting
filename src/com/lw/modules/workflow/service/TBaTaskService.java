/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.workflow.entity.TBaTask;
import com.lw.modules.workflow.dao.TBaTaskDao;

/**
 * 任务记录Service
 * @author 张旭东
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class TBaTaskService extends CrudService<TBaTaskDao, TBaTask> {

	public TBaTask get(String id) {
		return super.get(id);
	}
	
	public List<TBaTask> findList(TBaTask tBaTask) {
		return super.findList(tBaTask);
	}
	
	public Page<TBaTask> findPage(Page<TBaTask> page, TBaTask tBaTask) {
		return super.findPage(page, tBaTask);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaTask tBaTask) {
		
	    super.save(tBaTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaTask tBaTask) {
		super.delete(tBaTask);
	}
	
	public List<TBaTask> getTBaTaskList(TBaTask tBaTask){
		return dao.getTBaTaskList(tBaTask);
	}
	
	/**
	 * 查找上一结点的处理信息
	 * @param tBaTask
	 * @return
	 */
	public TBaTask getLastDual(TBaTask tBaTask){
	    return dao.getLastDual(tBaTask);
	}
}