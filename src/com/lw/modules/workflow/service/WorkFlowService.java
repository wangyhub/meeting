/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.service.BaseService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.sys.dao.UserDao;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.utils.UserUtils;
import com.lw.modules.workflow.entity.TBaActivity;
import com.lw.modules.workflow.entity.TBaNote;
import com.lw.modules.workflow.entity.TBaTask;
import com.lw.modules.workflow.entity.TBaTempActivity;
import com.lw.modules.workflow.entity.TBaTempNote;
import com.lw.modules.workflow.dao.TBaActivityDao;
import com.lw.modules.workflow.dao.TBaNoteDao;
import com.lw.modules.workflow.dao.TBaTaskDao;
import com.lw.modules.workflow.dao.TBaTempNoteDao;

/**
 * 任务记录Service
 * @author 张旭东
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class WorkFlowService extends BaseService{
	private String applyState= "01";//申请状态：01已保存，未提交02已提交03 04已受理05
	private String status = "1";//流程实例状态:1运行中2结束3终止4暂停
	private String SUCCESS = "1";//成功
	private String FAIL = "0";//失败
	@Autowired
	private TBaActivityDao tBaActivityDao;
	
	@Autowired
	private TBaNoteDao tBaNoteDao;
	
	@Autowired
	private TBaTaskDao tBaTaskDao;
	
	@Autowired
	private TBaTempNoteDao tBaTempNoteDao;
	
	@Autowired
    private TBaTempActivityService tBaTempActivityService;
	
	@Autowired
    private UserDao userDao;
	
	/**
	 * 创建流程
	 * @param pid 流程模板ID
	 * @param activityId 当前申请表的ID 
	 */
	@Transactional(readOnly = false)
	public String createWorkFlow(String logo,String applyId,String activityName) throws Exception{		
		try{
			//根据受理机构和流程标识符查询出对应的流程定义模板
			//查询流程模板定义表   
			TBaTempActivity tBaTempActivity = new TBaTempActivity();
			tBaTempActivity.setLogo(logo);
			tBaTempActivity = tBaTempActivityService.getByTBaTempActivity(tBaTempActivity);
			if(tBaTempActivity==null){
				return FAIL;
			}
			//1、保存流程实例表
			TBaActivity tBaActivity = new TBaActivity();
			tBaActivity.preInsert();
			tBaActivity.setTempActivityId(tBaTempActivity.getId());			
			applyState = "01";//已保存未提交
			tBaActivity.setApplyState(applyState);
			tBaActivity.setApplyId(applyId);//申请ID
			status = "1";//运行中
			tBaActivity.setStatus(status);
			tBaActivity.setActivityName(activityName);
			tBaActivity.setCompany(tBaTempActivity.getCompany());//提交机构
			tBaActivity.setUser(UserUtils.getUser());
			List<TBaTempNote> tBaTempNoteList = tBaTempActivity.getTBaTempNoteList();
            TBaTempNote tBaTempNote = new TBaTempNote();
            int breakFlag = 0;
            if(null != tBaTempNoteList){
                for (TBaTempNote tBaTempNoteTemp : tBaTempNoteList) {
                    if("1".equals(tBaTempNoteTemp.getStatus())){
                        tBaTempNote = tBaTempNoteTemp;
                        breakFlag = 1;
                        break;
                    }
                }
            }
            if(breakFlag == 0){
                throw new Exception();
            }
            // 在异常后面进行插入数据，可以避免回滚数据操作
            tBaActivityDao.insert(tBaActivity);
			//2、保存节点表
			TBaNote tBaNote = new TBaNote();
			tBaNote.preInsert();
			tBaNote.setTempNoteId(tBaTempNote.getId());
			tBaNote.setActivityId(tBaActivity);
			tBaNote.setName(tBaTempNote.getName());
			status = "1";//运行中
			tBaNote.setStatus(status);
			tBaNoteDao.insert(tBaNote);
			//3、保存任务表
			TBaTask tBaTask = new TBaTask();
			tBaTask.preInsert();
			tBaTask.setActivityId(tBaActivity.getId());
			tBaTask.setNoteId(tBaNote.getId());
			tBaTask.setNoteName(tBaNote.getName());
			tBaTask.setUpdateName(UserUtils.getUser().getName());
			tBaTask.setCreateName(UserUtils.getUser().getName());
			status = "1";//运行中
			tBaTask.setStatus(status);
			tBaTask.setDualType("02");
			tBaTaskDao.insert(tBaTask);
			return SUCCESS;
		}catch (Exception e) {				
		    logger.error(e.getMessage());
		    throw new Exception();
		}
	}
    /**
     * 发送到下一节点所有人
     * @param applyId
     * @param tempNoteId 下一节点
     */
	@Transactional(readOnly = false)
    public void sendNoteTask(String applyId, String tempNoteId){
	    //1、流程节点模板中查询下一节点(tempNoteId) 
        //必须在对t_ba_note表数据添加前，获得下一节点，否则查询语句失效
        if(StringUtils.isBlank(tempNoteId)){
            TBaTempNote tBaTempNote = getTBaTempNote(applyId);
            tempNoteId = tBaTempNote.getId();
        }
        //改变流程实例的状态，改为提交
        TBaActivity tBaActivity = new TBaActivity();
	    tBaActivity.setApplyId(applyId);
	    tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
	    applyState = "02";//申请状态：02已提交
	    tBaActivity.setApplyState(applyState);
	    tBaActivityDao.update(tBaActivity);//更新tBaActivity信息
	    //2、当前结点为运行中的改为已完成	    
        TBaNote tBaNote = new TBaNote();
        status = "1";
        tBaNote.setStatus(status);
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);//当前有效节点        
        tBaNote.preUpdate();//更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
    	//3、把当前用户任务记录状态为运行中的记录改为已完成
        User user = UserUtils.getUser();
        TBaTask tBaTask = new TBaTask();
        tBaTask.setNoteId(tBaNote.getId()); // 节点编号
        tBaTask.setUpdateBy(user); // 处理人
        tBaTask.setStatus("1");
        tBaTask = tBaTaskDao.getTBaTask(tBaTask);
        tBaTask.preUpdate();//更新任务节点修改信息
        tBaTask.setStatus("2");// 已完成         
        tBaTaskDao.update(tBaTask);
    	//4、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
    	//5、具体流程中添加下一个节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
        tBaNoteNext.setActivityId(tBaActivity);
        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext);
    	//6、创建任务记录（创建的记录数是和人员数是一一对应的）    	
    	List<User> userList = userDao.findUserByRoleId(tempNoteId);//查找下一节点的人    
    	//如果没有人就算归档,remark 显示的是roleId，这边如果一个人在两个角色里面，就出现两条记录（这边是为了需求原因，本系统采用了角色区分）
    	TBaTask tBaTaskNext = new TBaTask();
    	tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNoteNext.getId());
        tBaTaskNext.setNoteName(tBaNoteNext.getName());
        tBaTaskNext.setCreateName(user.getName());
        tBaTaskNext.setStatus("1");     
        for (User userTemp : userList) {
            tBaTaskNext.preInsert();
            tBaTaskNext.setUpdateBy(userTemp);
            tBaTaskNext.setUpdateName(userTemp.getName());
            tBaTaskNext.setDualType("02");//01 岗位内流转 02 下一节点 03 退回
            tBaTaskNext.setLastTaskId(tBaTask.getId());
            tBaTaskDao.insert(tBaTaskNext);
        }
        
    }
	   /**
     * 发送到下一节点指定人
     * @param applyId
     * @param tempNoteId
     * @param userId
     */
	@Transactional(readOnly = false)
    public void sendNoteTask(String applyId, String tempNoteId, String userId){
	    //1、流程节点模板中查询下一节点(tempNoteId) 
        //必须在对t_ba_note表数据添加前，获得下一节点，否则查询语句失效
        if(StringUtils.isBlank(tempNoteId)){
            TBaTempNote tBaTempNote = getTBaTempNote(applyId);
            tempNoteId = tBaTempNote.getId();
        }
	    //2、当前结点为运行中的改为已完成
        TBaActivity tBaActivity = new TBaActivity();
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        applyState = "02";//申请状态：02已提交
        tBaActivity.setApplyState(applyState);
        tBaActivityDao.update(tBaActivity);//更新tBaActivity信息
        TBaNote tBaNote = new TBaNote();
        status = "1";
        tBaNote.setStatus(status);
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);//当前有效节点        
        tBaNote.preUpdate(); // 更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);
        tBaNoteDao.update(tBaNote);
    	//3、把当前用户任务记录状态为运行中的记录改为已完成
        User user = UserUtils.getUser();
        TBaTask tBaTask = new TBaTask();
        tBaTask.setNoteId(tBaNote.getId()); // 节点编号
        tBaTask.setUpdateBy(user); // 处理人
        tBaTask.setStatus("1");
        tBaTask = tBaTaskDao.getTBaTask(tBaTask);
        tBaTask.setStatus("2");// 已完成         
        tBaTaskDao.update(tBaTask);
    	//4、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
        //5、具体流程中添加下一个节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
        tBaNoteNext.setActivityId(tBaActivity);
        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext); 	
    	//6、创建任务记录（这里根据直接发送给userId）
        User userNext = userDao.get(userId);
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.preInsert();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNoteNext.getId());
        tBaTaskNext.setNoteName(tBaNoteNext.getName());
        tBaTaskNext.setCreateName(user.getName());
        status = "1";//运行中
        tBaTaskNext.setStatus(status);
        tBaTaskNext.setUpdateBy(userNext);
        tBaTaskNext.setUpdateName(userNext.getName());
        tBaTaskNext.setDualType("02");
        tBaTaskNext.setLastTaskId(tBaTask.getId());
        tBaTaskDao.insert(tBaTaskNext);
    }	

    /**
     * 岗位内流转（指定人）
     * @param applyId
     * @param userId
     */
	@Transactional(readOnly = false)
    public void sendTask(String applyId,String userId){
    	//1、把当前用户任务记录状态为运行中的记录改为已完成
	    TBaActivity tBaActivity = new TBaActivity();
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        TBaNote tBaNote = new TBaNote();
        status = "1";
        tBaNote.setStatus(status);
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);//当前有效节点     

        User user = UserUtils.getUser();
        TBaTask tBaTask = new TBaTask();
        tBaTask.setNoteId(tBaNote.getId()); // 节点编号
        tBaTask.setUpdateBy(user); // 处理人
        tBaTask.setStatus("1");
        tBaTask = tBaTaskDao.getTBaTask(tBaTask);
        tBaTask.setStatus("2");// 已完成         
        tBaTaskDao.update(tBaTask);
    	//2、删除当前状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);
    	//3、创建任务记录
        User userNext = userDao.get(userId);
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.preInsert();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNote.getId());
        tBaTaskNext.setNoteName(tBaNote.getName());
        tBaTaskNext.setCreateName(user.getName());
        status = "1";//运行中
        tBaTaskNext.setStatus(status);
        tBaTaskNext.setUpdateBy(userNext);
        tBaTaskNext.setUpdateName(userNext.getName());
        tBaTaskNext.setDualType("01");
        tBaTaskNext.setLastTaskId(tBaTask.getId());
        tBaTaskDao.insert(tBaTaskNext);
    }
	
    /**
     * 流程撤回（谁发送的谁撤回，但是如果已经处理就无法撤回 1 成功 2 已处理无法撤回）
     * @param applyId 申请的Id
     * @param applyState 实例状态
     * @param currentUserId 当前处理用户的Id
     * @return
     */
	@Transactional(readOnly = false)
    //public int getBack(String activityId,String noteId,String currentUserId){
	public int getBack(String applyId,String applyState,String currentUserId){		
	    int result = 2;//状态1：正常撤回;状态2：撤回失败   
	    //实例
	    TBaActivity tBaActivity = new TBaActivity();
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        tBaActivity.setApplyState(applyState);
       
        //当前节点对象
        TBaNote tBaNote = new TBaNote();
        tBaNote.setActivityId(tBaActivity);
        tBaNote.setStatus("1");
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);        
        //当前未处理任务集合
        TBaTask tBaTask = new TBaTask();
        tBaTask.setActivityId(tBaActivity.getId());
        tBaTask.setStatus("1");
        List<TBaTask> tBaTaskList = tBaTaskDao.getTBaTaskList(tBaTask);
        String lastTaskId = tBaTaskList.get(0).getLastTaskId();//上一任务的ID   
        String userId = tBaTaskList.get(0).getCreateBy().getId();//上一任务的处理人的ID
        //上一任务
        TBaTask tBaTaskLast = tBaTaskDao.get(lastTaskId);
        //上一节点
        TBaNote tBaNoteLast = tBaNoteDao.get(tBaTaskLast.getNoteId());        
        //判断当前用户是不是上一处理任务的人员
        if(!userId.equals(currentUserId)){
        	return result;
        }
        tBaActivityDao.update(tBaActivity);        
        if(tBaNote.getId().equals(tBaNoteLast.getId())){//节点相同=岗位内流转
        	//1、删除当前任务，修改上一任务状态01        	
        	tBaTaskDao.delete(tBaTaskList.get(0));        	
        }else{//下一节点，非岗位内流转
        	TBaTask tBaTaskNew = new TBaTask();
        	tBaTaskNew.setNoteId(tBaNote.getId());
        	tBaTaskDao.delete(tBaTaskNew);//删除当前结点下面的任务记录        	
        	tBaNoteDao.delete(tBaNote);//删除当前结点
        	tBaNoteLast.setStatus("1");
        	tBaNoteDao.update(tBaNoteLast);//把上一节点改为有效节点
        }
        tBaTaskLast.setStatus("1");
    	tBaTaskDao.update(tBaTaskLast);
    	result = 1;
    	return result;
    }
	
    /**
     * 流程退回（当前处理人，把信息退回到上一处理人，其实就是做了一次插入操作）
     * @param activityId
     * @param currentUserId
     */
	@Transactional(readOnly = false)
    public void sendBack(String activityId,String currentUserId){
    	//1、首先如果处理人只有当前用户，可以退回，如果处理人不止当前用户，则不可以退回
    	
    	//2、上一节点的处理人和当前用户在同一节点，不存在退回，可以通过岗位内流转实现该功能
    	//3、上一节点的处理人和当前人员不在一个节点
    	//(1)把当前结点改为已完成
    	//(2)当前记录改为已完成
    	//(3)创建上一节点
    	//(4)创建上一记录
    }
	
	
	
	/**
     * 获得下一节点
     * @param applyId
     * @return
     */
    @Transactional(readOnly = false)
    public TBaTempNote getTBaTempNote(String applyId){
        //1、根据applyId得到流程定义
        TBaActivity tBaActivity = new TBaActivity();
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        TBaTempActivity tBaTempActivity = tBaTempActivityService.get(tBaActivity.getTempActivityId());
        //2、获得当前流程定义节点
        TBaNote tBaNote = new TBaNote();
        status = "1";
        tBaNote.setStatus(status);
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tBaNote.getTempNoteId());
        List<TBaTempNote> tBaTempNoteList = tBaTempActivity.getTBaTempNoteList();
        TBaTempNote tBaTempNoteNext = null;
        if(null != tBaTempNoteList){
            for (TBaTempNote tBaTempNoteTemp : tBaTempNoteList) {
                if("1".equals(tBaTempNoteTemp.getStatus()) 
                        && tBaTempNoteTemp.getPriority() > tBaTempNote.getPriority()){
                    tBaTempNoteNext = tBaTempNoteTemp;
                    break;
                }
            }
        }
        return tBaTempNoteNext;
    }
    

    
    /**
     * 流程的删除（已测试）
     * @param applyId
     */
    @Transactional(readOnly = false)
    public void deleteActivity(String applyId){
    	TBaActivity tBaActivity = new TBaActivity();    	
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);
        String activityId = tBaActivity.getId();
    	 //1、删除t_ba_task
        TBaTask tBaTask = new TBaTask();
        tBaTask.setActivityId(activityId);
        tBaTaskDao.delete(tBaTask);
        //2、删除t_ba_note
        TBaNote tBaNote = new TBaNote();
        tBaNote.setActivityId(tBaActivity);
        tBaNoteDao.delete(tBaNote);	
        //3、删除t_ba_activity        
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);        
        tBaActivityDao.delete(tBaActivity);       
    }

	/**
     * 发送到下一节点，材料核查（如果当前结点还有其他的为处理任务，只改变任务的状态）
     * @param applyId
     * @param tempNoteId
     * @param userId
     */
	@Transactional(readOnly = false)
    public void sendNoteTaskRole(String applyId, String tempNoteId,String roleId){
        //改变流程实例的状态，改为提交
        TBaActivity tBaActivity = new TBaActivity();
	    tBaActivity.setApplyId(applyId);
	    tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);  	   
	    //2、当前结点为运行中的改为已完成	    
        TBaNote tBaNote = new TBaNote();
        status = "1";
        tBaNote.setStatus(status);
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);//当前有效节点        
        tBaNote.preUpdate();//更新tBaNote信息
        status = "2"; // 已完成
        tBaNote.setStatus(status);        
    	//3、把当前用户任务记录状态为运行中的记录改为已完成
        User user = UserUtils.getUser();
        TBaTask tBaTask = new TBaTask();
        tBaTask.setNoteId(tBaNote.getId()); // 节点编号
        tBaTask.setUpdateBy(user); // 处理人
        tBaTask.setStatus("1");
        tBaTask.setRoleId(roleId);
        tBaTask = tBaTaskDao.getTBaTask(tBaTask);
        tBaTask.setStatus("2");// 已完成         
        tBaTaskDao.update(tBaTask);
        //如果当前为最后一条记录（除了当前角色的人员）就插入新记录，否则只修改task表
	    TBaTask tBaTaskCon = new TBaTask();
	    tBaTaskCon.setStatus("1");
	    tBaTaskCon.setActivityId(tBaActivity.getId());
	    tBaTaskCon.setRoleId(tBaTask.getRoleId());
	    List<TBaTask> tBaTaskList = tBaTaskDao.getTBaTaskList(tBaTaskCon);
        //2、删除当前同一角色中状态为运行中的任务记录        
        tBaTaskDao.deleteOthersTask(tBaTask);//这边过滤了角色
        if(tBaTaskList == null || tBaTaskList.size()==0){
        	 tBaNoteDao.update(tBaNote);//还有任务没有处理完，节点数据不保存
	    	//5、具体流程中添加下一个节点
	        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tempNoteId);
	        TBaNote tBaNoteNext = new TBaNote();
	        tBaNoteNext.preInsert();
	        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
	        tBaNoteNext.setActivityId(tBaActivity);
	        tBaNoteNext.setName(tBaTempNote.getName());
	        status = "1";//运行中
	        tBaNoteNext.setStatus(status);
	        tBaNoteDao.insert(tBaNoteNext);
	    	//6、创建任务记录（创建的记录数是和人员数是一一对应的）    	
	    	List<User> userList = userDao.findUserByRoleId(tempNoteId);//查找下一节点的人    
	    	//如果没有人就算归档,remark 显示的是roleId，这边如果一个人在两个角色里面，就出现两条记录（这边是为了需求原因，本系统采用了角色区分）
	    	TBaTask tBaTaskNext = new TBaTask();
	    	tBaTaskNext.setActivityId(tBaActivity.getId());
	        tBaTaskNext.setNoteId(tBaNoteNext.getId());
	        tBaTaskNext.setNoteName(tBaNoteNext.getName());
	        tBaTaskNext.setCreateName(user.getName());
	        tBaTaskNext.setStatus("1");     
	        for (User userTemp : userList) {
	            tBaTaskNext.preInsert();
	            tBaTaskNext.setUpdateBy(userTemp);
	            tBaTaskNext.setUpdateName(userTemp.getName());
	            tBaTaskNext.setDualType("02");//01 岗位内流转 02 下一节点 03 退回
	            tBaTaskNext.setLastTaskId(tBaTask.getId());
	            tBaTaskNext.setRoleId(userTemp.getRemarks());//由于一个人可能在两个不同的角色，所有当作两条记录处理，不同角色功能不同
	            tBaTaskDao.insert(tBaTaskNext);
	        }
        }
    }
	
	/**
     * 同一角色岗位内流转（指定人） 材料核查
     * @param applyId
     * @param userId 接收人
     */
	@Transactional(readOnly = false)
    public void sendRoleTask(String applyId,String userId){
	    TBaActivity tBaActivity = new TBaActivity();
        tBaActivity.setApplyId(applyId);
        tBaActivity = tBaActivityDao.getByTBaActivity(tBaActivity);//流程实例
        TBaNote tBaNote = new TBaNote();
        tBaNote.setStatus("1");
        tBaNote.setActivityId(tBaActivity);
        tBaNote = tBaNoteDao.getTBaNote(tBaNote);//当前有效节点     
        //把当前用户任务记录状态为运行中的记录改为已完成
        User user = UserUtils.getUser();
        TBaTask tBaTask = new TBaTask();
        tBaTask.setNoteId(tBaNote.getId()); // 节点编号
        tBaTask.setUpdateBy(user); // 处理人
        tBaTask.setStatus("1");
        tBaTask = tBaTaskDao.getTBaTask(tBaTask);
        tBaTask.setStatus("2");// 已完成         
        tBaTaskDao.update(tBaTask);
    	//2、删除当前同一角色中状态为运行中的任务记录
        tBaTaskDao.deleteOthersTask(tBaTask);//这边过滤了角色
    	//3、创建任务记录
        User userNext = userDao.get(userId);
        TBaTask tBaTaskNext = new TBaTask();
        tBaTaskNext.preInsert();
        tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNote.getId());       
        tBaTaskNext.setNoteName(tBaTask.getNoteName());//用角色名称
        tBaTaskNext.setRoleId(tBaTask.getRoleId());
        tBaTaskNext.setCreateName(user.getName());
        tBaTaskNext.setStatus("1");//运行中
        tBaTaskNext.setUpdateBy(userNext);
        tBaTaskNext.setUpdateName(userNext.getName());
        tBaTaskNext.setDualType("01");
        tBaTaskNext.setLastTaskId(tBaTask.getId());
        tBaTaskDao.insert(tBaTaskNext);
    }
    /*****************************************审核 会签********************************************/
	 /**
     * 发送到会签节点所选有人
     * @param applyId
     * @param tempNoteId
     * @param userId
     */
	@Transactional(readOnly = false)
    public void sendNoteTask(TBaTask tBaTask){
		TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());
		//会签人员
		String[] hqPerson = tBaTask.getStandby1().split(",");
		
        //1、把当前结点状态设置为2  
        TBaNote tBaNote =  tBaNoteDao.get(tBaTask.getNoteId());
        tBaNote.preUpdate(); // 更新tBaNote信息
        tBaNote.setStatus("2");
        tBaNoteDao.update(tBaNote);    	
        //5、具体流程中添加下一个节点
        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tBaTask.getStandby());//会签
       //3、把当前用户任务记录状态为运行中的记录改为已完成
        //tBaTask = tBaTaskDao.getTBaTask(tBaTask);
        tBaTask.setStatus("2");// 已完成  
        tBaTask.setStandby(tBaTask.getNextDualRole());//为领导节点IDtempId
        //委领导签批人员
		tBaTask.setStandby1(tBaTask.getNextDualPerson());//记录一下
        tBaTask.preUpdate();        
        tBaTaskDao.update(tBaTask);
        TBaNote tBaNoteNext = new TBaNote();
        tBaNoteNext.preInsert();
        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
        tBaNoteNext.setActivityId(tBaActivity);
        tBaNoteNext.setName(tBaTempNote.getName());
        status = "1";//运行中
        tBaNoteNext.setStatus(status);
        tBaNoteDao.insert(tBaNoteNext); 	
    	//6、创建任务记录（这里根据直接发送给userId）
        User user = UserUtils.getUser();
    	TBaTask tBaTaskNext = new TBaTask();
    	tBaTaskNext.setActivityId(tBaActivity.getId());
        tBaTaskNext.setNoteId(tBaNoteNext.getId());        
        tBaTaskNext.setCreateName(user.getName());
        tBaTaskNext.setStatus("1");     
        for (int i=0;i<hqPerson.length;i++) {
        	User user1 = UserUtils.get(hqPerson[i]);
            tBaTaskNext.preInsert();
            tBaTaskNext.setUpdateBy(user1);
            tBaTaskNext.setUpdateName(user1.getName());
            tBaTaskNext.setNoteName(tBaTempNote.getName());//节点名称
            tBaTaskNext.setDualType("02");//01 岗位内流转 02 下一节点 03 退回
            tBaTaskNext.setLastTaskId(tBaTask.getId());
            tBaTaskDao.insert(tBaTaskNext);
        }
    }	
	/**
	 * 会签并发
	 * @param applyId
	 */
	@Transactional(readOnly = false)
    public void sendNoteTaskBf(TBaTask tBaTask){
        //流程实例
        TBaActivity tBaActivity = tBaActivityDao.get(tBaTask.getActivityId());        
        //如果当前为最后一条记录（除了当前角色的人员）就插入新记录，否则只修改task表
	    TBaTask tBaTaskCon = new TBaTask();
	    tBaTaskCon.setStatus("1");
	    tBaTaskCon.setActivityId(tBaActivity.getId());
	    List<TBaTask> tBaTaskList = tBaTaskDao.getTBaTaskList(tBaTaskCon);
	    //当前记录改为已完成
	    tBaTask.setStatus("2");
        tBaTaskDao.update(tBaTask);
	    //记录只有当前1条记录有效的时候，说明最后一条记录
        if(tBaTaskList != null&&tBaTaskList.size()==1){      	
        	TBaNote tBaNote = tBaNoteDao.get(tBaTask.getNoteId());
        	tBaNote.setStatus("2");
        	tBaNoteDao.update(tBaNote);//还有任务没有处理完，节点数据不保存
	    	//5、具体流程中添加下一个节点
        	TBaTask tBaTaskLast = tBaTaskDao.get(tBaTask.getLastTaskId());
	        TBaTempNote tBaTempNote = tBaTempNoteDao.get(tBaTaskLast.getStandby());//委领导签批
	        TBaNote tBaNoteNext = new TBaNote();
	        tBaNoteNext.preInsert();
	        tBaNoteNext.setTempNoteId(tBaTempNote.getId());
	        tBaNoteNext.setActivityId(tBaActivity);
	        tBaNoteNext.setName(tBaTempNote.getName());
	        status = "1";//运行中
	        tBaNoteNext.setStatus(status);
	        tBaNoteDao.insert(tBaNoteNext);
	    	//6、创建任务记录（创建的记录数是和人员数是一一对应的）    		
	    	User user = UserUtils.getUser();
	    	User userNext = UserUtils.get(tBaTaskLast.getStandby1());
	    	TBaTask tBaTaskNext = new TBaTask();
	    	tBaTaskNext.setActivityId(tBaActivity.getId());
	        tBaTaskNext.setNoteId(tBaNoteNext.getId());
	        tBaTaskNext.setNoteName(tBaNoteNext.getName());
	        tBaTaskNext.setCreateName(user.getName());
	        tBaTaskNext.setStatus("1");
	        tBaTaskNext.preInsert();
            tBaTaskNext.setUpdateBy(userNext);
            tBaTaskNext.setUpdateName(userNext.getName());
            tBaTaskNext.setDualType("02");//01 岗位内流转 02 下一节点 03 退回
            tBaTaskNext.setLastTaskId(tBaTask.getId());	            
            tBaTaskDao.insert(tBaTaskNext);

        }
    }
}