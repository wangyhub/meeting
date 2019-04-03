/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.service.CrudService;
import com.lw.common.utils.IdGen;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.dao.TBaSonPublicBeanDao;
import com.lw.modules.base.entity.TBaSonPublicBean;
import com.lw.modules.base.entity.TBaTableConfig;
import com.lw.modules.base.entity.TBaTableField;


/**
 * 功能测试数据录入Service
 * @author weixm
 * @version 2016-04-15
 */
@Service
@Transactional(readOnly = true)
public class TBaSonPublicBeanService extends CrudService<TBaSonPublicBeanDao, TBaSonPublicBean> {
	
	public List<TBaSonPublicBean> findList(TBaSonPublicBean tBaSonPublicBean) {
		return super.findList(tBaSonPublicBean);
	}
	
	@Transactional(readOnly = false)
	public void save(List<TBaSonPublicBean> tbaBaSonPublicBeanList,TBaTableConfig tBaTableConfig) {
		//保存主表
		TBaSonPublicBean tBaSonPublicBean = null;
		for(int j = 0; j < tbaBaSonPublicBeanList.size(); j++){
			tBaSonPublicBean = tbaBaSonPublicBeanList.get(j);
			if(StringUtils.isEmpty(tBaSonPublicBean.getStr1())){
				StringBuffer sb = new StringBuffer();
				StringBuffer strValue = new StringBuffer();
				sb.append("insert into ").append(tBaTableConfig.getTableName()).append(" ( id,");
				List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
				int strnum = 1,datenum = 0,numnum = 0;
				for(int i = 1; i< list.size();i++){
					sb.append(list.get(i).getFieldCode()).append(",");
					if(list.get(i).getFieldType().equals("01")){//字符串
						strnum = strnum + 1;
						strValue.append("'").append(tBaSonPublicBean.getStrValue("str"+strnum)).append("',");
					}
					if(list.get(i).getFieldType().equals("04")){//日期
						datenum = datenum + 1;
						Date dateValue = tBaSonPublicBean.getDateValue("date"+datenum);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String date = sdf.format(dateValue);
						strValue.append(" to_date('").append(date).append("','yyyy-MM-dd HH24:mi:ss'),");
					}
					if(list.get(i).getFieldType().equals("02") || list.get(i).getFieldType().equals("03")){//数字
						numnum = numnum + 1;
						strValue.append(tBaSonPublicBean.getIntValue("num"+numnum)).append(",");
					}
				}
				String sqlValue = strValue.toString().substring(0, strValue.toString().length()-1);
				String sql = sb.toString().substring(0, sb.toString().length()-1);
				StringBuffer sb2 = new StringBuffer(sql);
				sb2.append(" )").append(" values ").append(" ( '").append(IdGen.uuid()).append("',").append(sqlValue).append(" )");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sql", sb2.toString());
				dao.save(map);
			}else if(StringUtils.isNotEmpty(tBaSonPublicBean.getStr1())){
				StringBuffer sb = new StringBuffer();
				sb.append("update ").append(tBaTableConfig.getTableName()).append(" set ");
				List<TBaTableField> list = tBaTableConfig.getTBaTableFieldList();
				int strnum = 1,datenum = 0,numnum = 0;
				for(int i = 1; i< list.size();i++){
					if(list.get(i).getFieldType().equals("01")){//字符串
						strnum = strnum + 1;
						sb.append(list.get(i).getFieldCode()).append(" = '").append(tBaSonPublicBean.getStrValue("str"+strnum)).append("',");
					}
					if(list.get(i).getFieldType().equals("04")){//日期
						datenum = datenum + 1;
						Date dateValue = tBaSonPublicBean.getDateValue("date"+datenum);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String date = sdf.format(dateValue);
						sb.append(list.get(i).getFieldCode()).append(" = ").append(" to_date('").append(date).append("','yyyy-MM-dd HH24:mi:ss'),");
					}
					if(list.get(i).getFieldType().equals("02") || list.get(i).getFieldType().equals("03")){//数字
						numnum = numnum + 1;
						sb.append(list.get(i).getFieldCode()).append(" = ").append(tBaSonPublicBean.getIntValue("num"+numnum)).append(",");
					}
				}
				String sql = sb.toString().substring(0, sb.toString().length()-1)+" where id = '"+tBaSonPublicBean.getStr1()+"'";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sql", sql);
				dao.save(map);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(String id, TBaTableConfig tBaTableConfig) {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ").append(tBaTableConfig.getTableName()).append(" where id ='").append(id).append("'");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sb.toString());
		dao.delete(map);
	}

	@Transactional(readOnly = false)
	public List<TBaSonPublicBean> exeSql(String sql){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		return dao.exeSql(map);
	}
	
	
	@Transactional(readOnly = false)
	public List<TBaSonPublicBean> doSql(TBaTableConfig tBaTableConfig,String tableId){
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
		String sql = str + " from "+tBaTableConfig.getTableName()+" where table_id="+"'"+tableId+"'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		return dao.doSql(map);
	}
}