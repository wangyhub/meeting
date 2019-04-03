/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbMeeting;
import com.lw.modules.meeting.entity.model.CommonModel;

/**
 * 会议管理DAO接口
 * @author meijx
 * @version 2019-03-06
 */
@MyBatisDao
public interface TbMeetingDao extends CrudDao<TbMeeting> {

    public void updateStatus(TbMeeting entity);

    public CommonModel showMeetingMap(String meetingId);
}