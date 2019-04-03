/**
 * Copyright &copy; 2012-2014 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.dao.file;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.meeting.entity.file.TbAnnexFile;
import com.lw.modules.meeting.entity.model.FileContent;

import java.util.List;

/**
 * 会议资料DAO接口
 * @author meijx
 * @version 2019-03-16
 */
@MyBatisDao
public interface TbAnnexFileDao extends CrudDao<TbAnnexFile> {
    List<FileContent> findAAA(String meetId);

    List<TbAnnexFile> findById(String meetId);
}