/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbAgendaSeat;
import com.lw.modules.meeting.entity.meet.TbMeeting;

import java.util.List;

/**
 * 座次安排DAO接口
 * @author meijx
 * @version 2019-03-12
 */
@MyBatisDao
public interface TbAgendaSeatDao extends CrudDao<TbAgendaSeat> {
    public List<String> findSeatNoList(String agendaId);
}