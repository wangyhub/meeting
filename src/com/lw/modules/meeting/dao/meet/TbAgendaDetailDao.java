/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.model.AgendaDetail;
import com.lw.modules.meeting.entity.meet.TbAgendaDetail;

import java.util.List;

/**
 * 日程安排DAO接口
 * @author meijx
 * @version 2019-03-11
 */
@MyBatisDao
public interface TbAgendaDetailDao extends CrudDao<TbAgendaDetail> {

    public List<TbAgendaDetail> findListByAgendaId(String agendaId);

    public List<AgendaDetail> getAgendaListByAgendaId(String agendaId);
}