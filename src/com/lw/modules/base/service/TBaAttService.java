/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.entity.TBaFile;
import com.lw.modules.base.entity.TBaAtt;
import com.lw.modules.base.dao.TBaAttDao;

/**
 * 附近配置Service
 * @author handf
 * @version 2015-08-09
 */
@Service
@Transactional(readOnly = true)
public class TBaAttService extends CrudService<TBaAttDao, TBaAtt> {

    @Autowired
    private TBaFileService tBaseAttachmentService;
    
	public TBaAtt get(String id) {
		return super.get(id);
	}
	
	public List<TBaAtt> findList(TBaAtt tBaAtt) {
		return super.findList(tBaAtt);
	}
	
	public Page<TBaAtt> findPage(Page<TBaAtt> page, 
	        TBaAtt tBaAtt) {
		return super.findPage(page, tBaAtt);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaAtt tBaAtt, HttpServletRequest request) {
	    String fileId = request.getParameter("fileId");
        tBaAtt.setFileId(fileId);
	    super.save(tBaAtt);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaAtt tBaAtt) {
	    TBaFile tBaFile = new TBaFile();
	    if (null != tBaAtt && StringUtils.isNotEmpty(tBaAtt.getFileId())) {
	        tBaFile.setId(tBaAtt.getFileId());
	        tBaseAttachmentService.delete(tBaFile);
        }
	    super.delete(tBaAtt);
	}
	
	@Transactional(readOnly = false)
    public void deleteFile(HttpServletRequest request) {
        String attId = request.getParameter("attId");
        String id = request.getParameter("id");
        TBaAtt defineAttachment = dao.get(id);
        TBaFile attachment = new TBaFile();
        attachment.setId(attId);
        tBaseAttachmentService.delete(attachment);
        dao.deleteFile(defineAttachment);
    }
}