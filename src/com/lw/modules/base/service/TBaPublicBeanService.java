/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.dao.TBaPublicBeanDao;
import com.lw.modules.base.dao.TBaSonPublicBeanDao;
import com.lw.modules.base.entity.TBaPublicBean;
import com.lw.modules.base.entity.TBaTableConfig;
import com.lw.modules.base.entity.TBaTableField;



/**
 * 功能测试数据录入Service
 * @author weixm
 * @version 2016-04-15
 */
@Service
@Transactional(readOnly = true)
public class TBaPublicBeanService extends CrudService<TBaPublicBeanDao, TBaPublicBean> {
	@Autowired
	private TBaTableConfigService tBaTableConfigService;
	
	@Autowired
	private TBaSonPublicBeanDao tBaSonPublicBeanDao;
	
	
	public List<TBaPublicBean> findList(TBaPublicBean tBaPublicBean) {
		return super.findList(tBaPublicBean);
	}
	
	public Page<TBaPublicBean> findPage(Page<TBaPublicBean> page, TBaPublicBean tBaPublicBean, String tableConfigId, Map<String, Object> map) {
		//1 根据表名获取该表单的配置参数		
		TBaTableConfig tBaTableConfig = tBaTableConfigService.get(tableConfigId);
		List<TBaTableField> findData = tBaTableConfigService.getFindData(tableConfigId);
		//查询SQL拼接
		StringBuffer sql = new StringBuffer();
		StringBuffer str = new StringBuffer();
		sql.append("select ");
		List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
		TBaTableField tBaTableField = null;
		int strnum = 0,datenum = 0,numnum = 0;
		for(int i = 0; i< list.size();i++){
			tBaTableField = list.get(i);
			if(tBaTableField.getFieldType().equals("01")){//字符串
				strnum = strnum + 1;
				sql.append(tBaTableField.getFieldCode()).append(" str").append(strnum).append(",");
				str.append("str").append(strnum).append(",");
			}
			if(tBaTableField.getFieldType().equals("04")){//日期
				datenum = datenum + 1;
				sql.append(tBaTableField.getFieldCode()).append(" date").append(datenum).append(",");
				str.append("date").append(datenum).append(",");
			}
			if(tBaTableField.getFieldType().equals("02") || tBaTableField.getFieldType().equals("03")){//数字
				numnum = numnum + 1;
				sql.append(tBaTableField.getFieldCode()).append(" num").append(numnum).append(",");
				str.append("num").append(numnum).append(",");
			}
		}
		String querySql = sql.toString();
		querySql = querySql.substring(0,querySql.length()-1);
		String[] split = str.toString().substring(0, str.toString().length()-1).split(",");
		List<String> asList = Arrays.asList(split);
		querySql = querySql + " from " + tBaTableConfig.getTableName() + " where 1=1 ";
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < findData.size();i++){
			if((map != null && map.get(findData.get(i).getFieldCode()) != null && map.get(findData.get(i).getFieldCode()) != "")||(map != null && (map.get(findData.get(i).getFieldCode()+"_start") != null || map.get(findData.get(i).getFieldCode()+"_end") != null))){
				if("01".equals(findData.get(i).getQueryType())){
					sb.append(" and ").append(findData.get(i).getFieldCode()).append(" like '%").append((String)map.get(findData.get(i).getFieldCode())).append("%'");
				}else if("02".equals(findData.get(i).getQueryType())){
					sb.append(" and ").append(findData.get(i).getFieldCode()).append(" = '").append((String)map.get(findData.get(i).getFieldCode())).append("'");
				}else if("03".equals(findData.get(i).getQueryType())){
					String start = findData.get(i).getFieldCode()+"_start";
					String end = findData.get(i).getFieldCode()+"_end";
					if("04".equals(findData.get(i).getFieldType())){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String startTime = "";
						String endTime = "";
						if(map.get(start)!=null){
							startTime = sdf.format(map.get(start));
						}
						if(map.get(end)!=null){
							endTime = sdf.format(map.get(end));
						}
						if(StringUtils.isNotEmpty(startTime) && StringUtils.isEmpty(endTime)){
							sb.append(" and ").append(findData.get(i).getFieldCode()).append(" > to_date('").append(startTime).append("','yyyy-MM-dd HH24:mi:ss')");
						}else if(StringUtils.isNotEmpty(endTime) && StringUtils.isEmpty(startTime)){
							sb.append(" and ").append(findData.get(i).getFieldCode()).append(" < to_date('").append(endTime).append("','yyyy-MM-dd HH24:mi:ss')");
						}else if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
							sb.append(" and ").append(findData.get(i).getFieldCode()).append(" between to_date('").append(startTime).append("','yyyy-MM-dd HH24:mi:ss')").append(" and to_date('").append(endTime).append("','yyyy-MM-dd HH24:mi:ss')");
						}
					}else{
						if(StringUtils.isNotEmpty((String)map.get(start)) && StringUtils.isEmpty((String)map.get(end))){
							sb.append(" and ").append(findData.get(i).getFieldCode()).append(" > '").append(map.get(start)).append("'");
						}else if(StringUtils.isNotEmpty((String)map.get(end)) && StringUtils.isEmpty((String)map.get(start))){
							sb.append(" and ").append(findData.get(i).getFieldCode()).append(" < '").append(map.get(end)).append("'");
						}else if(StringUtils.isNotEmpty((String)map.get(start)) && StringUtils.isNotEmpty((String)map.get(end))){
							sb.append(" and ").append(findData.get(i).getFieldCode()).append(" between '").append(map.get(start)).append("' and '").append(map.get(end)).append("'");
						}
					}
				}
			}
		}
		querySql = querySql + sb.toString();
		tBaPublicBean.setQueryStr(querySql);
		tBaPublicBean.setList(asList);
		return super.findPage(page, tBaPublicBean);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaPublicBean tBaPublicBean,TBaTableConfig tBaTableConfig) {
		//保存主表
		StringBuffer existSql = new StringBuffer();
		existSql.append("select * from ").append(tBaTableConfig.getTableName()).append(" where id='").append(tBaPublicBean.getStr1()).append("'");
		Map<String, Object> existMap = new HashMap<String, Object>();
		existMap.put("sql", existSql.toString());
		TBaPublicBean existOrNot = dao.doSql(existMap);
		if(existOrNot == null){
			StringBuffer sb = new StringBuffer();
			StringBuffer strValue = new StringBuffer();
			sb.append("insert into ").append(tBaTableConfig.getTableName()).append(" (");
			List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
			int strnum = 0,datenum = 0,numnum = 0;
			for(int i = 0; i< list.size();i++){
				sb.append(list.get(i).getFieldCode()).append(",");
				if(list.get(i).getFieldType().equals("01")){//字符串
					strnum = strnum + 1;
					strValue.append("'").append(tBaPublicBean.getStrValue("str"+strnum)).append("',");
				}
				if(list.get(i).getFieldType().equals("04")){//日期
					datenum = datenum + 1;
					Date dateValue = tBaPublicBean.getDateValue("date"+datenum);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(dateValue);
					strValue.append(" to_date('").append(date).append("','yyyy-MM-dd HH24:mi:ss'),");
				}
				if(list.get(i).getFieldType().equals("02") || list.get(i).getFieldType().equals("03")){//数字
					numnum = numnum + 1;
					strValue.append(tBaPublicBean.getIntValue("num"+numnum)).append(",");
				}
			}
			String sqlValue = strValue.toString().substring(0, strValue.toString().length()-1);
			String sql = sb.toString().substring(0, sb.toString().length()-1);
			StringBuffer sb2 = new StringBuffer(sql);
			sb2.append(" )").append(" values ").append(" ( ").append(sqlValue).append(" )");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sql", sb2.toString());
			dao.save(map);
		}else if(existOrNot != null){
			StringBuffer sb = new StringBuffer();
			sb.append("update ").append(tBaTableConfig.getTableName()).append(" set ");
			List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
			int strnum = 1,datenum = 0,numnum = 0;
			for(int i = 1; i< list.size();i++){
				if(list.get(i).getFieldType().equals("01")){//字符串
					strnum = strnum + 1;
					sb.append(list.get(i).getFieldCode()).append(" = '").append(tBaPublicBean.getStrValue("str"+strnum)).append("',");
				}
				if(list.get(i).getFieldType().equals("04")){//日期
					datenum = datenum + 1;
					Date dateValue = tBaPublicBean.getDateValue("date"+datenum);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = sdf.format(dateValue);
					sb.append(list.get(i).getFieldCode()).append(" = ").append(" to_date('").append(date).append("','yyyy-MM-dd HH24:mi:ss'),");
				}
				if(list.get(i).getFieldType().equals("02") || list.get(i).getFieldType().equals("03")){//数字
					numnum = numnum + 1;
					sb.append(list.get(i).getFieldCode()).append(" = ").append(tBaPublicBean.getIntValue("num"+numnum)).append(",");
				}
			}
			String sql = sb.toString().substring(0, sb.toString().length()-1)+" where id = '"+tBaPublicBean.getStr1()+"'";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sql", sql);
			dao.save(map);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaPublicBean tBaPublicBean,TBaTableConfig tBaTableConfig) {
		List<TBaTableConfig> sonTableInfoList = tBaTableConfigService.findSonTableInfoByTableName(tBaTableConfig.getTableName());
		if(sonTableInfoList.size() != 0){
			TBaTableConfig sonTBaTableConfig = null;
			for(int i = 0; i < sonTableInfoList.size(); i++){
				sonTBaTableConfig = sonTableInfoList.get(i);
			}
			StringBuffer sbf = new StringBuffer();
			sbf.append("delete from ").append(sonTBaTableConfig.getTableName()).append(" where table_id = '").append(tBaPublicBean.getStr1()).append("'");
			Map<String, Object> sonMap = new HashMap<String, Object>();
			sonMap.put("sql", sbf.toString());
			tBaSonPublicBeanDao.delete(sonMap);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ").append(tBaTableConfig.getTableName()).append(" where id ='").append(tBaPublicBean.getStr1()).append("'");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sb.toString());
		dao.delete(map);
	}

	@Transactional(readOnly = false)
	public List<TBaPublicBean> exeSql(String sql){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		return dao.exeSql(map);
	}
	
	
	@Transactional(readOnly = false)
	public TBaPublicBean doSql(TBaTableConfig tBaTableConfig,String tableId){
		StringBuffer sb = new StringBuffer();
		sb.append("select  ");
		List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
		TBaTableField tBaTableField = null;
		int strnum = 0,datenum = 0,numnum = 0;
		for(int i = 0; i< list.size();i++){
			tBaTableField = list.get(i);
			if(tBaTableField.getFieldType().equals("01")){//字符串
				strnum = strnum + 1;
				sb.append(tBaTableField.getFieldCode()).append(" str").append(strnum).append(",");
			}
			if(tBaTableField.getFieldType().equals("04")){//日期
				datenum = datenum + 1;
				sb.append(tBaTableField.getFieldCode()).append(" date").append(datenum).append(",");
			}
			if(tBaTableField.getFieldType().equals("02") || tBaTableField.getFieldType().equals("03")){//数字
				numnum = numnum + 1;
				sb.append(tBaTableField.getFieldCode()).append(" num").append(numnum).append(",");
			}
		}
		String str = sb.toString().substring(0, sb.toString().length()-1);
		String sql = str + " from "+tBaTableConfig.getTableName()+" where id="+"'"+tableId+"'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		return dao.doSql(map);
	}
}