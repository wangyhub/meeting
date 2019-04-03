/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.sys.entity.Office;
import com.lw.modules.workflow.entity.TBaActivity;
import com.lw.modules.workflow.entity.TBaNote;
import com.lw.modules.workflow.entity.TBaNoteRole;
import com.lw.modules.workflow.entity.TBaTempActivity;
import com.lw.modules.workflow.dao.TBaActivityDao;
import com.lw.modules.workflow.dao.TBaNoteDao;
import com.lw.modules.workflow.dao.TBaNoteRoleDao;
import com.lw.modules.workflow.dao.TBaTempActivityDao;
import com.lw.modules.workflow.entity.TBaTempNote;
import com.lw.modules.workflow.dao.TBaTempNoteDao;

/**
 * 流程管理Service
 * @author handf
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class TBaTempActivityService extends CrudService<TBaTempActivityDao, TBaTempActivity> {

	@Autowired
	private TBaTempNoteDao tBaTempNoteDao;
	
	@Autowired
    private TBaActivityDao tBaActivityDao;
	
	@Autowired
    private TBaNoteDao tBaNoteDao;
		
	@Autowired
	private TBaNoteRoleDao tBaNoteRoleDao;
	
	public TBaTempActivity get(String id) {
		TBaTempActivity tBaTempActivity = super.get(id);
		List<TBaTempNote> tBaTempNoteList = new ArrayList<TBaTempNote>();
		List<TBaTempNote> tBaTempNotes = tBaTempNoteDao.findList(new TBaTempNote(tBaTempActivity));
		for (TBaTempNote tBaTempNote : tBaTempNotes) {
		    if(null != tBaTempNote && StringUtils.isNotBlank(tBaTempNote.getId())){
		        List<String> roleIdList = tBaTempNoteDao.queryNoteRoleByNoteId(tBaTempNote.getId());
		        tBaTempNote.setRoleIdList(roleIdList);
		    }
		    tBaTempNoteList.add(tBaTempNote);
        }
		tBaTempActivity.setTBaTempNoteList(tBaTempNoteList);
		return tBaTempActivity;
	}
	
	public List<TBaTempActivity> findList(TBaTempActivity tBaTempActivity) {
		return super.findList(tBaTempActivity);
	}
	
	public Page<TBaTempActivity> findPage(Page<TBaTempActivity> page, TBaTempActivity tBaTempActivity) {	   
	    return super.findPage(page, tBaTempActivity);
	}
	
	@Transactional(readOnly = false)
	public String saveFlag(TBaTempActivity tBaTempActivity) {
		String result = "";// 保存结果  "" 为正常保存； "xxxx"为某节点保存失败
	    // 1、保存实例表
		super.save(tBaTempActivity);			
		Map<String, Object> condition = new HashMap<String, Object>();
		// 2、保存节点表【新增、修改数据（内容修改，删除）】	
		for (TBaTempNote tBaTempNote : tBaTempActivity.getTBaTempNoteList()){
			if (tBaTempNote.getId() == null){
				continue;
			}		
			// id不存在，新增数据
			if (StringUtils.isBlank(tBaTempNote.getId())){
				tBaTempNote.setActivityId(tBaTempActivity);
				tBaTempNote.preInsert();
				tBaTempNoteDao.insert(tBaTempNote);
			}else{
			    // 修改的时候删除的节点下有数据的话，不能删除
			    TBaNote tBaNote = new TBaNote();
		        if("1".equals(tBaTempNote.getDelFlag())){
	                tBaNote.setTempNoteId(tBaTempNote.getId());
		            List<TBaNote> tBaNoteList = tBaNoteDao.countTBaNote(tBaNote);
		            if(null != tBaNoteList && tBaNoteList.size() > 0){
		                tBaTempNote.setDelFlag("0");
		                result = tBaTempNote.getName();
		                break;
		            }
		        }
			    tBaTempNote.setActivityId(tBaTempActivity);
				tBaTempNote.preUpdate();
				tBaTempNoteDao.update(tBaTempNote);
				// 删除节点编号下所有的角色
				condition.put("noteId", tBaTempNote.getId());
				tBaTempNoteDao.deleteNoteRoleByMap(condition);
			}
			// flag=1时，已删除数据保存，节点角色权限不增加
			if(!TBaTempNote.DEL_FLAG_NORMAL.equals(tBaTempNote.getDelFlag())){
				continue;
			}
			// 3、保存节点角色关系表
			List<String> roleIdList = tBaTempNote.getRoleIdList();
			if(null != roleIdList){
    			for (String roleId : roleIdList) {
    			    if(null != roleId && StringUtils.isNotBlank(roleId)){
    			        TBaNoteRole tBaNoteRole = new TBaNoteRole();
                        tBaNoteRole.preInsert();
                        tBaNoteRole.setNoteId(tBaTempNote.getId());
                        tBaNoteRole.setRoleId(roleId);
                        tBaNoteRoleDao.insert(tBaNoteRole);
    			    }
                }
			}
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public String deleteFlag(TBaTempActivity tBaTempActivity) {
		// 判断定义流程模板下是否有具体流程使用
	    String result = ""; // 删除结果   0：成功； 1：失败。
	    TBaActivity tBaActivity = new TBaActivity();
	    tBaActivity.setTempActivityId(tBaTempActivity.getId());
	    List<TBaActivity> tBaActivityList = tBaActivityDao.countTBaActivity(tBaActivity);
	    if(null != tBaActivityList && tBaActivityList.size() > 0){
	        result = "1";
	    }else{
	        super.delete(tBaTempActivity);
	        tBaTempNoteDao.delete(new TBaTempNote(tBaTempActivity));
	        Map<String, Object> condition = new HashMap<String, Object>();
	        for (TBaTempNote tBaTempNote : tBaTempActivity.getTBaTempNoteList()){
	            // 删除节点编号下所有的角色
	            condition.put("noteId", tBaTempNote.getId());
	            tBaTempNoteDao.deleteNoteRoleByMap(condition);
	        }
	        result = "0";
	    }
	    return result;
	}
	
	@Transactional(readOnly = false)
	public String checkActivityName(HttpServletRequest request){
	    String result = "false";
        String flag = request.getParameter("flag");                 // 查找falg : 0 不存在 1 存在
        String aid = request.getParameter("aid");                   // 流程ID
        String activityName = request.getParameter("activityName"); // 流程定义名称
        String cid = request.getParameter("cid");                   // 公司ID
        TBaTempActivity tBaTempActivity = new TBaTempActivity();
        tBaTempActivity.setId(aid);
        Office company = new Office();
        company.setId(cid);
        tBaTempActivity.setCompany(company);
        tBaTempActivity.setActivityName(activityName);
        if(StringUtils.isNotEmpty(activityName)) {    
            List<TBaTempActivity> list =  dao.checkActivtyName(tBaTempActivity);
            if(null != list && 0 < list.size()) {
                result = "false";// 已存在
            }else{
                result = "true";// 不存在
            }
        }
        if(StringUtils.isNotEmpty(flag) && "1".equals(flag) ){
            if(result == "false"){
                result = "true";
            }else{
                result = "false";
            }
        }
        return result;
	}
	
	@Transactional(readOnly = false)
    public String checkActivtyLogo(HttpServletRequest request){
        String result = "false";
        String flag = request.getParameter("flag"); // 查找falg : 0 不存在 1 存在
        String aid = request.getParameter("aid");   // 流程ID
        String logo = request.getParameter("logo"); // 流程标识
        String cid = request.getParameter("cid");   // 公司ID 
        TBaTempActivity tBaTempActivity = new TBaTempActivity();
        tBaTempActivity.setId(aid);
        Office company = new Office();
        company.setId(cid);
        tBaTempActivity.setCompany(company);
        tBaTempActivity.setLogo(logo);
        if(StringUtils.isNotEmpty(logo)) {    
            List<TBaTempActivity> list =  dao.checkActivtyLogo(tBaTempActivity);
            if(null != list && 0 < list.size()) {
                result = "false";// 已存在
            }else{
                result = "true";// 不存在
            }
        }
        if(StringUtils.isNotEmpty(flag) && "1".equals(flag) ){
            if(result == "false"){
                result = "true";
            }else{
                result = "false";
            }
        }
        return result;
    }
	
	public TBaTempActivity getByTBaTempActivity(TBaTempActivity tBaTempActivity){
	   	TBaTempActivity tBaTempActivityTemp = dao.getByTBaTempActivity(tBaTempActivity);
		TBaTempActivity tBaTempActivityResult = get(tBaTempActivityTemp.getId());
	    return tBaTempActivityResult;
	 }
}