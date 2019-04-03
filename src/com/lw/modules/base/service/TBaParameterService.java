/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.base.entity.TBaParameter;
import com.lw.modules.base.dao.TBaParameterDao;

/**
 * 参数配置Service
 * @author handf
 * @version 2015-08-11
 */
@Service
@Transactional(readOnly = true)
public class TBaParameterService extends CrudService<TBaParameterDao, TBaParameter> {

	public TBaParameter get(String id) {
		return super.get(id);
	}
	
	public List<TBaParameter> findList(TBaParameter tBaParameter) {
		return super.findList(tBaParameter);
	}
	
	public Page<TBaParameter> findPage(Page<TBaParameter> page, TBaParameter tBaParameter) {
		return super.findPage(page, tBaParameter);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaParameter tBaParameter) {
		super.save(tBaParameter);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaParameter tBaParameter) {
		super.delete(tBaParameter);
	}
	
}