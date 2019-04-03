/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.meet;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.meet.TbJoin;
import com.lw.modules.meeting.entity.model.JoinDepartment;
import com.lw.modules.meeting.entity.model.JoinInfo;
import com.lw.modules.meeting.entity.model.MeetingCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参会人管理DAO接口
 * @author meijx
 * @version 2019-03-07
 */
@MyBatisDao
public interface TbJoinDao extends CrudDao<TbJoin> {

    //获取报名单位下拉列表
    public List<JoinDepartment> getDepartmentList();

    //插入报名数据
    public void addTbJoin(JoinInfo joinInfo);

    //修改报名数据
    public void updateJoinInfo(JoinInfo joinInfo);

    //获取报名信息
    public JoinInfo getJoinInfo(@Param("meetingId") String meetingId, @Param("userId") String userId);

    //获取报名用户信息
    public JoinInfo getUserInfo(String userId);

    public List<MeetingCompany> getMeetingCompanyList(String meetingId);
}