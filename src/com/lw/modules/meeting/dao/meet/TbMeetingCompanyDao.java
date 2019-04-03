/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbMeetingCompany;

import java.util.List;

/**
 * 会议管理DAO接口
 * @author meijx
 * @version 2019-03-06
 */
@MyBatisDao
public interface TbMeetingCompanyDao extends CrudDao<TbMeetingCompany> {

    public List<TbMeetingCompany> findListByMeetingId(String meetingId);

}