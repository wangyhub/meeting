/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.base.entity.TBaField;
import com.lw.modules.base.entity.TBaMainSql;
import com.lw.modules.base.dao.TBaFieldDao;
import com.lw.modules.base.dao.TBaMainSqlDao;

/**
 * 动态查询Service 
 * @author handf
 * @version 2016-03-14
 */
@Service
@Transactional(readOnly = true)
public class TBaMainSqlService extends CrudService<TBaMainSqlDao, TBaMainSql> {

	@Autowired
	private TBaFieldDao tBaFieldDao;
	
	public TBaMainSql get(String id) {
		TBaMainSql tBaMainSql = super.get(id);
		tBaMainSql.setFieldList(tBaFieldDao.findList(new TBaField(tBaMainSql)));
		return tBaMainSql;
	}
	
	public List<TBaMainSql> findList(TBaMainSql tBaMainSql) {
		return super.findList(tBaMainSql);
	}
	
	public Page<TBaMainSql> findPage(Page<TBaMainSql> page, TBaMainSql tBaMainSql) {
		return super.findPage(page, tBaMainSql);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaMainSql tBaMainSql) {
		//1、保存主表（t_ba_main_sql）
		super.save(tBaMainSql);
	   
	    //2、保存子表(t_ba_field)先删后增
        //删除
		TBaField tBaFieldTemp = new TBaField();
        tBaFieldTemp.setMain(tBaMainSql);
        tBaFieldDao.delete(tBaFieldTemp);
        //新增
	    List<TBaField> fieldList = tBaMainSql.getFieldList();
	    if(fieldList != null && fieldList.size() > 0){
	    	for (TBaField tBaField : fieldList) {
	    		tBaField.setMain(tBaMainSql);
	    		tBaField.preInsert();
				tBaFieldDao.insert(tBaField);
			}
	    }
	    
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaMainSql tBaMainSql) {
		super.delete(tBaMainSql);
		//注：此处不需要调用删除，因为数据库建立了级联删除关系
	}
	
	/**
	 * 检测是否是可执行的SQL语句，如果是进行解析操作
	 * @param headSql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> checkIsSql(String headSql){
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<String, String>(1);
		//此处只需要达到sql的校验功能，不需要查询结果数量个，所以加上1 = 2 
		headSql = replaceString(headSql);
		String headSqlCount = "select count(*) from (" + headSql + " and 1 = 2 )" ;
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("headSql", headSqlCount);
		String result = "false";
		try {
			result = dao.checkIsSql(headMap) + "";
			if("0".equals(result)){
				result = "true";
			}
			map = getResultTypeList(headSql);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", "0");//success 对应值为0 失败，1 成功
			map.putAll(json);
		} 
		return map;
	}
	
	/**
	 * 进行sql类型解析操作
	 * @param tBaMainSql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getResultTypeList(String headSql){
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<String, String>(1);
        //创建视图
		createTempView(headSql);
		
		//查询结果集结构
		Map<String, String> headMap = new HashMap<String, String>();
        headMap.put("tableName", "V_TEMP_MAIN_SQL");//此处一定要注意大写，因为数据库存储的表名均为大写
        List<TBaField> tBaFields = dao.getResultTypeList(headMap);
        
        //讲数据进行格式转化
        JSONArray msgArray = new JSONArray();        
        for (TBaField tBaField : tBaFields) {
        	JSONObject jsonInfo = new JSONObject();
			jsonInfo.put("codeName", tBaField.getCodeName());
			jsonInfo.put("physicsType", tBaField.getPhysicsType());
			jsonInfo.put("physicsLength", tBaField.getPhysicsLength());
			jsonInfo.put("fieldSort", tBaField.getFieldSort());
			msgArray.add(jsonInfo);
		}
        json.put("msgArray", msgArray);
        json.put("success", "1");//success 对应值为0 失败，1 成功
		map.putAll(json);

        
        //删除视图
		deleteTempView();

		return map;
	}
	
	/**
	 * 创建临时视图
	 * @param tBaMainSql
	 */
	public void createTempView(String headSql){
		Map<String, String> headMap = new HashMap<String, String>();
        String createViewSql = replaceString(headSql);
        createViewSql = "create or replace view v_temp_main_sql AS " + createViewSql + " and 1 = 2";
        headMap.put("createViewSql", createViewSql);
        dao.createTempView(headMap);
	}
	
	/**
	 * 删除临时视图
	 * @param tBaMainSql
	 */
	public void deleteTempView(){
		Map<String, String> headMap = new HashMap<String, String>();
        String deleteViewSql = "drop view V_TEMP_MAIN_SQL";
        headMap.put("deleteViewSql", deleteViewSql);
		dao.deleteTempView(headMap);
	}
	
	/**
	 * 特殊字符串替换
	 * @param headSql
	 * @return
	 */
	public String replaceString(String headSql){
		headSql = headSql.replace("&quot;", "\"");//将转义的双引号转化为"
		headSql = headSql.replace("&lt;", "<");//将转义的双引号转化为<
		headSql = headSql.replace("&gt;", ">");//将转义的双引号转化为>
		headSql = headSql.replace("&amp;", "&");//将转义的双引号转化为&
		headSql = headSql.replace("&nbsp;", " ");//将转义的双引号转化为空格
		return headSql;
	}
	
}