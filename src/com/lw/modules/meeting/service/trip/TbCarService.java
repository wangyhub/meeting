/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.trip;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.trip.TbCar;
import com.lw.modules.meeting.dao.trip.TbCarDao;

/**
 * 车辆信息Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbCarService extends CrudService<TbCarDao, TbCar> {

	public TbCar get(String id) {
		return super.get(id);
	}
	
	public List<TbCar> findList(TbCar tbCar) {
		return super.findList(tbCar);
	}
	
	public Page<TbCar> findPage(Page<TbCar> page, TbCar tbCar) {
		return super.findPage(page, tbCar);
	}
	
	@Transactional(readOnly = false)
	public void save(TbCar tbCar) {
		super.save(tbCar);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbCar tbCar) {
		super.delete(tbCar);
	}
	
}