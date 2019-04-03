/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.stay;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.stay.TbHotel;
import com.lw.modules.meeting.dao.stay.TbHotelDao;

/**
 * 酒店信息Service
 * @author meijx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class TbHotelService extends CrudService<TbHotelDao, TbHotel> {

	public TbHotel get(String id) {
		return super.get(id);
	}
	
	public List<TbHotel> findList(TbHotel tbHotel) {
		return super.findList(tbHotel);
	}
	
	public Page<TbHotel> findPage(Page<TbHotel> page, TbHotel tbHotel) {
		return super.findPage(page, tbHotel);
	}
	
	@Transactional(readOnly = false)
	public void save(TbHotel tbHotel) {
		super.save(tbHotel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbHotel tbHotel) {
		super.delete(tbHotel);
	}
	
}