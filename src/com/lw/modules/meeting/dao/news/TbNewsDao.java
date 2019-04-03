/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.news;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.model.TbNewsContent;
import com.lw.modules.meeting.entity.news.TbNews;

import java.util.List;

/**
 * 新闻动态DAO接口
 * @author meijx
 * @version 2019-03-19
 */
@MyBatisDao
public interface TbNewsDao extends CrudDao<TbNews> {
    List<TbNewsContent> findAll(String newsId);

    TbNewsContent findById(String id);

    public void updateStatus(TbNews entity);
}