/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.stay;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.model.StayInfo;
import com.lw.modules.meeting.entity.stay.TbStay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 住宿信息DAO接口
 * @author meijx
 * @version 2019-03-13
 */
@MyBatisDao
public interface TbStayDao extends CrudDao<TbStay> {
    public List<StayInfo> getStayInfo (@Param("meetingId")String meetingId, @Param("userId")String userId);
}