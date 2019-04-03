/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import java.util.List;
import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.model.AgendaInfo;
import com.lw.modules.meeting.entity.meet.TbMeetingAgenda;

/**
 * 日程安排DAO接口
 * @author meijx
 * @version 2019-03-11
 */
@MyBatisDao
public interface TbMeetingAgendaDao extends CrudDao<TbMeetingAgenda> {

    //根据会议ID查询议程集合
    public List<AgendaInfo> getAgendaList(String meetingId);

    //根据主键ID查询议程信息
    public AgendaInfo getAgendaInfoById(String agendaId);
	
}