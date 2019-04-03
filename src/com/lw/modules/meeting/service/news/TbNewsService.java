/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.news;

import java.util.List;

import com.lw.modules.meeting.entity.meet.TbMeeting;
import com.lw.modules.meeting.entity.model.TbNewsContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.news.TbNews;
import com.lw.modules.meeting.dao.news.TbNewsDao;

/**
 * 新闻动态Service
 * @author meijx
 * @version 2019-03-19
 */
@Service
@Transactional(readOnly = true)
public class TbNewsService extends CrudService<TbNewsDao, TbNews> {

	@Autowired
	private TbNewsDao tbNewsDao;

	public TbNews get(String id) {
		return super.get(id);
	}
	
	public List<TbNews> findList(TbNews tbNews) {
		return super.findList(tbNews);
	}
	
	public Page<TbNews> findPage(Page<TbNews> page, TbNews tbNews) {
		return super.findPage(page, tbNews);
	}
	
	@Transactional(readOnly = false)
	public void save(TbNews tbNews) {
		super.save(tbNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbNews tbNews) {
		super.delete(tbNews);
	}

	@Transactional(readOnly = false)
	public void updateStatus(TbNews tbNews) {
		tbNews.preUpdate();
		tbNewsDao.updateStatus(tbNews);
	}

	public List<TbNewsContent> findAll(String newsId) {
		List<TbNewsContent> tbNewsContentList = tbNewsDao.findAll(newsId);
		return tbNewsContentList;
	}

	public TbNewsContent findById(String id) {
		TbNewsContent tbNewsContent =	tbNewsDao.findById(id);
		return tbNewsContent;
	}
}