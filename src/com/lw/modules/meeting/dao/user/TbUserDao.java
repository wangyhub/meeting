/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.user;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbJoin;
import com.lw.modules.meeting.entity.model.*;
import com.lw.modules.meeting.entity.user.TbUser;
import org.apache.ibatis.annotations.Param;

/**
 * 参会用户DAO接口
 * @author meijx
 * @version 2019-03-08
 */
@MyBatisDao
public interface TbUserDao extends CrudDao<TbUser> {
    public LoginUser findByPhone(String phone);

    TbSeatContent findById(String userId);

    public JoinUser getUserById(String userId);

    public void updateTbUser(JoinInfo joinInfo);

    TbSelfContent findMyData(@Param("userId")String userId, @Param("meetingId")String meetingId);
}