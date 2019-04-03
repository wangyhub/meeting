/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.entity.CgAutoListConstant;
import com.lw.modules.base.entity.TBaTableConfig;
import com.lw.modules.base.dao.TBaTableConfigDao;
import com.lw.modules.base.entity.TBaTableField;
import com.lw.modules.base.dao.TBaTableFieldDao;


/**
 * 表单配置Service
 * @author weixm
 * @version 2016-03-29
 */
@Service
@Transactional(readOnly = true)
public class TBaTableConfigService extends CrudService<TBaTableConfigDao, TBaTableConfig> {

	@Autowired
	private TBaTableFieldDao tBaTableFieldDao;
	
	//同步方式：普通同步
	private static final String SYN_NORMAL = "normal";
	//同步方式：强制同步
	private static final String SYN_FORCE = "force";
	
	public TBaTableConfig get(String id) {
		TBaTableConfig tBaTableConfig = super.get(id);
		if(tBaTableConfig.getTBaTableFieldList()==null||tBaTableConfig.getTBaTableFieldList().size()==0){
		   tBaTableConfig.setTBaTableFieldList(tBaTableFieldDao.findList(new TBaTableField(tBaTableConfig)));
		}
		return tBaTableConfig;
	}
	
	public List<TBaTableConfig> findList(TBaTableConfig tBaTableConfig) {
		return super.findList(tBaTableConfig);
	}
	
	public Page<TBaTableConfig> findPage(Page<TBaTableConfig> page, TBaTableConfig tBaTableConfig) {
		return super.findPage(page, tBaTableConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaTableConfig tBaTableConfig) {
		//保存主表
		super.save(tBaTableConfig);
		//根据主表ID删除子表信息
//		TBaTableField tbaTableFieldPara = new TBaTableField();
//		tbaTableFieldPara.setTableId(tBaTableConfig);
//		tBaTableFieldDao.deleteByConfigId(tbaTableFieldPara);
	    //添加子表信息
		for (TBaTableField tBaTableField : tBaTableConfig.getTBaTableFieldList()){
			if(StringUtils.isEmpty(tBaTableField.getId())){
				if (TBaTableField.DEL_FLAG_NORMAL.equals(tBaTableField.getDelFlag())){
					tBaTableField.setTableId(tBaTableConfig);
//					tBaTableField.setOldFieldCode(tBaTableField.getFieldCode());
					tBaTableField.preInsert();
					tBaTableFieldDao.insert(tBaTableField);
				}
			}else{
				tBaTableFieldDao.update(tBaTableField);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaTableConfig tBaTableConfig) {
		StringBuffer sb = new StringBuffer();
		sb.append("drop table ").append(tBaTableConfig.getTableName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sb.toString());
		dao.exeSql(map);
		super.delete(tBaTableConfig);
		tBaTableFieldDao.delete(new TBaTableField(tBaTableConfig));
	}
	
	@Transactional(readOnly = false)
	public void deleteTBaTableFieldById(String id){
		TBaTableField tBaTableField = new TBaTableField(id);
		tBaTableFieldDao.deleteTBaTableFieldById(tBaTableField);
	}
	
	@Transactional(readOnly = false)
	public Integer checkTableName(String tableName){
		Integer count = dao.checkTableName(tableName);
		return count;
	}
	
	public List<TBaTableField> getFindData(String tableId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isQueryCondition", "01");
		map.put("tableId", tableId);
		return tBaTableFieldDao.getFindData(map);
	}
	
	public List<TBaTableConfig> findSonTableInfoByTableName(String tableName){
		List<TBaTableConfig> list = dao.findSonTableInfoByTableName(tableName);
		for(int i = 0; i < list.size(); i++){
			List<TBaTableField> colmunList = tBaTableFieldDao.getColumnByTableId(list.get(i).getId());
			list.get(i).setTBaTableFieldList(colmunList);
		}
		return list;
	}
	
	/**
	 * 同步数据库
	 * @param tBaTableConfig
	 * @return
	 */
	public boolean dbSynch(TBaTableConfig tBaTableConfig,String synMethod) throws Exception{
		boolean result = false; 
		String tableName = tBaTableConfig.getTableName();
		if(SYN_NORMAL.equals(synMethod)){
			//普通方式同步：如果存在，更新表结构
			//1、判断表是否存在
			if (judgeTableIsExit(tableName)) {//存在
				// 更新表操作
				 updateTable(tBaTableConfig);				
			} else {
				// 不存在的情况下，创建新表
			    createTable(tBaTableConfig);
			}				
		}else if(SYN_FORCE.equals(synMethod)){
			//强制方式同步：直接删除表，然后创建新表
			if (judgeTableIsExit(tableName)) {//存在,直接删除
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sql", "drop table "+tableName);
				dao.exeSql(map);
			}
			createTable(tBaTableConfig);
		}
		return result;
	}
	
	/**
	 * 判断数据库中的表是否存在
	 * @param tableName
	 * @return
	 */
	public Boolean judgeTableIsExit(String tableName) {
		int i = dao.isExistTable(tableName);
		if(i==0){
			return false;
		}else{
		    return true;
		}
	}
	/**
	 * 更新数据库表操作
	 * @param table
	 * @param session
	 * @return
	 * @throws DBException
	 */
	public void updateTable(TBaTableConfig tBaTableConfig) throws Exception{
		//StringBuilder sb = new StringBuilder();
		String tableName =tBaTableConfig.getTableName().toUpperCase();
		String alterTable="alter table  "+tableName+" ";
		List<String> strings = new ArrayList<String>();
	       //对表的修改列和删除列的处理
	      try {
	    	 //获取数据库中列的信息
			 Map<String, TBaTableField> dataBaseColumnMetaMap = getColumnMetadataFormDataBase(tBaTableConfig);
			 //获取定义表中定义的列的信息
			 Map<String, TBaTableField> cgFormColumnMetaMap = getColumnMetadataFormCgForm(tBaTableConfig);
			 //获取定义表中列的对应关系
			 Map<String,String> newAndOldFieldMap =getNewAndOldFieldName(tBaTableConfig);
			 for(TBaTableField tBaTableField :tBaTableConfig.getTBaTableFieldList()){
					tBaTableField.setOldFieldCode(tBaTableField.getFieldCode());
					tBaTableFieldDao.update(tBaTableField);
				}
//			 //表如果不存在该列，则要对表做修改、增加、删除该列动作 此处无法处理删除的列，因为在这个循环中无法获得该列
			 for (String columnName : cgFormColumnMetaMap.keySet()) {
				 if(!dataBaseColumnMetaMap.containsKey(columnName)){//1、定义的数据中字段不存在已经创建的表中，根据定义的数据写出增加的脚本
					//如果旧列中包含这个列名，说明是修改名称的
					 TBaTableField cgFormColumnMeta = cgFormColumnMetaMap.get(columnName);
					 //修改已有表中的列名
					if(newAndOldFieldMap.containsKey(columnName)&&(dataBaseColumnMetaMap.containsKey(newAndOldFieldMap.get(columnName)))){
						TBaTableField dataColumnMeta = dataBaseColumnMetaMap.get(newAndOldFieldMap.get(columnName));
						strings.add(alterTable+ "RENAME COLUMN  "+dataColumnMeta.getFieldCode()+" TO "+cgFormColumnMeta.getFieldCode()+"");
						//执行完成之后修改成一致 fildname和oldfieldname
						//updateFieldName(columnName, cgFormColumnMeta.getColumnId(),session);
						//修改字段名之后继续判断值有没有变化,有变化继续修改值
						if (!cgFormColumnMeta.compareField().equals(dataColumnMeta.compareField())) {
								strings.add(alterTable+" MODIFY   "+getUpdateFieldDesc(cgFormColumnMeta,dataColumnMeta)+"");
						}
						//判断注释是不是相同,修改注释
						if(!cgFormColumnMeta.compareField().equals(dataColumnMeta.compareField())){
							strings.add("COMMENT ON COLUMN "+tBaTableConfig.getTableName()+"."+dataColumnMeta.getFieldCode()+" IS '" +cgFormColumnMeta.getFieldName()+"'");
						}
					}else{//不包含就是要增加
						strings.add(alterTable+" ADD  "+getAddFieldDesc(cgFormColumnMeta)+"");
						if(StringUtils.isNotEmpty(cgFormColumnMeta.getRemarks())){
							strings.add("COMMENT ON COLUMN "+tBaTableConfig.getTableName()+"."+cgFormColumnMeta.getFieldCode()+" IS '" +cgFormColumnMeta.getFieldName()+"'");
						}
					}
				}else {//已经存在的判断是否修改了类型长度。。
					//判断是否类型、长度、是否为空、精度被修改，如果有修改则处理修改
					TBaTableField dataColumnMeta = dataBaseColumnMetaMap.get(columnName);
					TBaTableField cgFormColumnMeta = cgFormColumnMetaMap.get(columnName);
					dataColumnMeta.getClass().isAssignableFrom(cgFormColumnMeta.getClass());
					//如果不相同，则表示有变化，则需要修改
					if (!cgFormColumnMeta.compareField().equals(dataColumnMeta.compareField())) {
						strings.add(alterTable+" MODIFY   "+getUpdateFieldDesc(cgFormColumnMeta,dataColumnMeta)+"");
					}
					if(!cgFormColumnMeta.compareField().equals(dataColumnMeta.compareField())){
						strings.add("COMMENT ON COLUMN "+tBaTableConfig.getTableName()+"."+cgFormColumnMeta.getFieldCode()+" IS '" +cgFormColumnMeta.getFieldName()+"'");
					}
				}
				
			}
			 
			//删除数据库的列
			 //要判断这个列不是修改的
			 for (String columnName : dataBaseColumnMetaMap.keySet()) {
				if ((!cgFormColumnMetaMap.containsKey(columnName.toLowerCase()))&& (!newAndOldFieldMap.containsValue(columnName.toLowerCase()))) {
					strings.add(alterTable+" DROP COLUMN "+columnName.toUpperCase()+"");
				}
			}
			 
		} catch (SQLException e1) {
			throw new RuntimeException();
		}
		logger.info(strings.toString());
		if(strings.size()>0){
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i < strings.size(); i++){
				sb.append(strings.get(i));
				if(i != (strings.size() - 1)){
					sb.append(";");
				}
			}
			String[] split = sb.toString().split(";");
			for (String string : split) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sql", string);
				dao.exeSql(map);
			}
		}
		tBaTableConfig.setMatchDataBase("01");
		save(tBaTableConfig);
	}
	/**
	 * 返回定义列中列名的新和旧的对应关系
	 * @param table
	 * @return
	 */
	public static Map<String, String> getNewAndOldFieldName(TBaTableConfig tBaTableConfig){
		Map<String, String> map = new HashMap<String, String>();
		List<TBaTableField> tBaTableField = tBaTableConfig.getTBaTableFieldList();
		for (TBaTableField ttf : tBaTableField) {
			map.put(ttf.getFieldCode(), ttf.getOldFieldCode());
		}
		return map;//map;
	}
	/**
	 * 创建数据库表
	 * @param tBaTableConfig
	 */
	public void createTable(TBaTableConfig tBaTableConfig) throws Exception{
		//1、组织建表的语句
		StringBuffer createTableSql = new StringBuffer();
		StringBuffer foreign = new StringBuffer();
		createTableSql.append("create table ").append(tBaTableConfig.getTableName()).append("(");
		for(TBaTableField tBaTableField :tBaTableConfig.getTBaTableFieldList()){
			tBaTableField.setOldFieldCode(tBaTableField.getFieldCode());
			tBaTableFieldDao.update(tBaTableField);
			createTableSql.append(tBaTableField.getFieldCode().toString());
			//字段类型01 String 02 integer 03 Double 04 Date
			if(tBaTableField.getFieldType().equals("02") || tBaTableField.getFieldType().equals("03")){
				createTableSql.append(" number(").append(tBaTableField.getFieldLength()).append(")");
			}else if(tBaTableField.getFieldType().equals("04")){
				createTableSql.append(" Date");
			}else{//默认01字符串类型
				createTableSql.append(" VARCHAR2(").append(tBaTableField.getFieldLength()).append(")");
			}
			if(tBaTableField.getIsNull().equals("02")){
				createTableSql.append(" not null");
			}
			//如果是附表，进行外键sql语句拼接
			if(StringUtils.isNotEmpty(tBaTableField.getFartherTable()) && StringUtils.isNotEmpty(tBaTableField.getFartherField())){
				foreign.append("alter table "+ tBaTableConfig.getTableName() +" add constraint fk_"+ tBaTableConfig.getTableName() +" foreign key ("+tBaTableField.getFieldCode()+") references "+tBaTableField.getFartherTable()+" (ID)");
			}
			createTableSql.append(",");
		}
		createTableSql.append(" PRIMARY KEY (id))");
		//执行SQL语句方法
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", createTableSql.toString());
		
		dao.exeSql(map);
		//为表添加外键
		if(StringUtils.isNotEmpty(foreign.toString())){
			Map<String, Object> fkMap = new HashMap<String, Object>();
			fkMap.put("sql", foreign.toString());
			dao.exeSql(fkMap);
		}
		tBaTableConfig.setMatchDataBase("01");
		save(tBaTableConfig);
	}
	/**
	 * 获取数据库中存在表的列的描述
	 * @param tBaTableConfig
	 * @return
	 * @throws Exception
	 */
	public Map<String, TBaTableField> getColumnMetadataFormDataBase(TBaTableConfig tBaTableConfig) throws Exception{
		Map<String, TBaTableField> tBaTableFieldMap = new HashMap<String, TBaTableField>();
		List<TBaTableField> tBaTableField = tBaTableFieldDao.findDataBaseTableField(tBaTableConfig.getTableName());
		for (TBaTableField ttf : tBaTableField) {
			tBaTableFieldMap.put(ttf.getFieldCode().toLowerCase(), ttf);
		}
		return tBaTableFieldMap;
	}
	
	/**
	 * 获取定义表中定义的列的信息
	 * @param tBaTableConfig
	 * @return
	 */
	public Map<String, TBaTableField> getColumnMetadataFormCgForm(TBaTableConfig tBaTableConfig){
		Map<String, TBaTableField> tBaTableFieldMap = new HashMap<String, TBaTableField>();
		List<TBaTableField> tBaTableField = tBaTableFieldDao.findDataFormTableField(tBaTableConfig);
		for (TBaTableField ttf : tBaTableField) {
			tBaTableFieldMap.put(ttf.getFieldCode().toLowerCase(), ttf);
		}
		return tBaTableFieldMap;
		
	}
	
//	/**
//	 * 更新主表的附表串-用于表配置提交时调用
//	 * 
//	 * @param entity
//	 *            表单配置
//	 * @return 更新是否成功
//	 */
//	public boolean appendSubTableStr4Main(TBaTableConfig tBaTableConfig) {
//		// step.1 获取本表的名称
//		String thisSubTable = tBaTableConfig.getTableName();
//		List<TBaTableField> columns = tBaTableConfig.getTBaTableFieldList();
//		// step.2 扫描字段配置，循环处理填有主表以及主表字段的条目
//		for (TBaTableField ttf : columns) {
//			String mainT = ttf.getFartherTable();
//			String mainF = ttf.getFartherField();
//			if (!StringUtils.isEmpty(mainT) && !StringUtils.isEmpty(mainF)) {
//				TBaTableField mainE = this.getCgFormHeadByTableName(mainT);
//				if (mainE == null) {
//					continue;
//				}
//				// step.4 追加处理主表的附表串
//				String subTableStr = String
//						.valueOf(mainE.getSubTableStr() == null ? "" : mainE
//								.getSubTableStr());
//				// step.5 判断是否已经存在于附表串
//				if (!subTableStr.contains(thisSubTable)) {
//					// step.6 追加到附表串
//					if (!StringUtils.isEmpty(subTableStr)) {
//						subTableStr += "," + thisSubTable;
//					} else {
//						subTableStr += thisSubTable;
//					}
//					mainE.setSubTableStr(subTableStr);
////					logger.info("--主表" + mainE.getTableName() + "的附表串："
////							+ mainE.getSubTableStr());
//				}
//				// step.7 更新主表的表配置
//				this.updateTable(mainE, "sign");
//			}
//		}
//		return true;
//	}
	
	public TBaTableField getCgFormHeadByTableName(String tableName) {
		List<TBaTableField> list = tBaTableFieldDao.findDataBaseTableField(tableName);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	private String getAddFieldDesc(TBaTableField columnMeta) {
		String result ="";
		if(columnMeta.getFieldType().equalsIgnoreCase("varchar2")){
			result = columnMeta.getFieldCode()+" varchar2("+columnMeta.getFieldLength()+")";
		}else if(columnMeta.getFieldType().equalsIgnoreCase("date")){
			result = columnMeta.getFieldCode()+" date";
		}else if(columnMeta.getFieldType().equalsIgnoreCase("number")){
			result = columnMeta.getFieldCode()+" NUMBER("+columnMeta.getFieldLength()+")";
		}else if(columnMeta.getFieldType().equalsIgnoreCase("text")){ 
			result = columnMeta.getFieldCode()+" CLOB";
		}else if(columnMeta.getFieldType().equalsIgnoreCase("blob")){
			result = columnMeta.getFieldCode()+" BLOB";
		}
//		result += (StringUtils.isNotEmpty(columnMeta.getFieldDefault())?" DEFAULT "+columnMeta.getFieldDefault():" ");
		result += (columnMeta.getIsNull().equals("Y")?" NULL":" NOT NULL");
		return result;
	}
	
	private String getUpdateFieldDesc(TBaTableField cgformcolumnMeta,TBaTableField datacolumnMeta) {
		String result ="";
		String isnull="";
		//oracle对于是否为空必须跟原来的比对
		if (!datacolumnMeta.getIsNull().equals(cgformcolumnMeta.getIsNull())) {
			isnull=(cgformcolumnMeta.getIsNull().equals("Y")?"NULL":"NOT NULL");
		}
		if(cgformcolumnMeta.getFieldType().equalsIgnoreCase("varchar2")||cgformcolumnMeta.getFieldType().equalsIgnoreCase("text")){
			result = cgformcolumnMeta.getFieldCode()+" varchar2("+cgformcolumnMeta.getFieldLength()+")"+isnull;
			
		}else if(cgformcolumnMeta.getFieldType().equalsIgnoreCase("date")){
			result = cgformcolumnMeta.getFieldCode()+" date "+isnull;
			
		}else if(cgformcolumnMeta.getFieldType().equalsIgnoreCase("number")){
			result = cgformcolumnMeta.getFieldCode()+" NUMBER("+cgformcolumnMeta.getFieldLength()+") "+isnull;
			
		}
//		result += (StringUtils.isNotEmpty(cgformcolumnMeta.getFieldDefault())?" DEFAULT "+cgformcolumnMeta.getFieldDefault():" ");
		result += isnull;
		return result;
	}

	/**
	 * tableName 表单名
	 */
	public Map<String, Object> queryConfigs(String tableName) {
		//step.1 要返回的配置数据
		Map<String, Object> configs = new HashMap<String,Object>();
		//step.2 获取动态表配置
		TBaTableConfig tBaTableConfig = null;
		try{
			//根据tableName找到对象
			tBaTableConfig = dao.findTBaTableConfigByTableName(tableName);
			tBaTableConfig.setTBaTableFieldList(tBaTableFieldDao.findList(new TBaTableField(tBaTableConfig)));
			configs = loadConfigs(configs,tBaTableConfig);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("没有找到该动态列表");
		}
		return configs;
	}
	
	private Map<String, Object> loadConfigs(Map<String, Object> configs, TBaTableConfig tBaTableConfig) {
		//获取动态表明细配置
		List<TBaTableField> columns = tBaTableConfig.getTBaTableFieldList();
		configs.put(CgAutoListConstant.CONFIG_ID, tBaTableConfig.getTableName());
		configs.put(CgAutoListConstant.CONFIG_NAME, tBaTableConfig.getContent());
		configs.put(CgAutoListConstant.TABLENAME,tBaTableConfig.getTableName());
		configs.put(CgAutoListConstant.CONFIG_ISPAGINATION,tBaTableConfig.getIsPagination());
		configs.put(CgAutoListConstant.FILEDS,columns);
		return configs;
	}
	
//	public List findDataList(String tableName,List list){
//		StringBuffer sb = new StringBuffer();
//		sb.append("select ");
//		for(int i = 0;i < list.size();i++){
//			Map<String, Object> mapKey  = (HashMap<String, Object>)list.get(i);
//			sb.append((String)mapKey.get("field_id"));
//			if(i != (list.size()-1)){
//				sb.append(",");
//			}
//		}
//		sb.append(" from "+tableName);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sql", sb.toString());
//		List dataList = dao.findListBySql(map);
//		return dataList;
//	}
	


}