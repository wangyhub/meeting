/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbMeetingPlace;
import com.lw.modules.meeting.entity.model.AgendaInfo;
import com.lw.modules.meeting.entity.model.TbSeatContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会场管理DAO接口
 * @author meijx
 * @version 2019-03-09
 */
@MyBatisDao
public interface TbMeetingPlaceDao extends CrudDao<TbMeetingPlace> {

    List<AgendaInfo> findAll(String seatId);

    TbSeatContent findById(@Param("id")String id,@Param("userId") String userId);
}