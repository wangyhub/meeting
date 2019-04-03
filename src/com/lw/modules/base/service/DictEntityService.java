/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.service.CrudService;
import com.lw.modules.base.dao.DictEntityDao;
import com.lw.modules.base.entity.DictEntity;

/**
 * 附近配置Service
 * @author handf
 * @version 2015-08-09
 */
@Service
@Transactional(readOnly = true)
public class DictEntityService extends CrudService<DictEntityDao, DictEntity> {

	public List<DictEntity> queryDic(String dicTable,String dicCode,String dicText){
		return dao.queryDic(dicTable, dicCode, dicText);
	}
   
}