/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.lw.common.config.Global;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.meeting.entity.back.TbBackstage;
import com.lw.modules.meeting.dao.back.TbBackstageDao;

/**
 * 数据维护Service
 * @author meijx
 * @version 2019-03-21
 */
@Service
@Transactional(readOnly = true)
public class TbBackstageService extends CrudService<TbBackstageDao, TbBackstage> {

	public TbBackstage get(String id) {
		return super.get(id);
	}
	
	public List<TbBackstage> findList(TbBackstage tbBackstage) {
		return super.findList(tbBackstage);
	}
	
	public Page<TbBackstage> findPage(Page<TbBackstage> page, TbBackstage tbBackstage) {
		return super.findPage(page, tbBackstage);
	}
	
	@Transactional(readOnly = false)
	public void save(TbBackstage tbBackstage) {
		super.save(tbBackstage);
	}
	
	@Transactional(readOnly = false)
	public void delete(TbBackstage tbBackstage) {
		super.delete(tbBackstage);
	}

	public TbBackstage analyzeSql(String sql){
		TbBackstage stage = new TbBackstage();
		if(sql==null || "".equals(sql.trim())){
			return null;
		}

		String cloneSql = sql.trim().toLowerCase();
		cloneSql = cloneSql.replaceAll("\\s+","|").replaceAll("\\(","|");
		String type = "";
		String table = "";

		if(cloneSql.startsWith("insert")){
			type = "写入";
			cloneSql = cloneSql.substring(cloneSql.indexOf("|into|")+6);
			if(cloneSql.indexOf("|")!=-1){
				table = cloneSql.substring(0,cloneSql.indexOf("|"));
			}
		}
		else if(cloneSql.startsWith("update")){
			type = "更新";
			cloneSql = cloneSql.substring(7);
			if(cloneSql.indexOf("|")!=-1){
				table = cloneSql.substring(0,cloneSql.indexOf("|"));
			}
		}
		else if(cloneSql.startsWith("delete")){
			type = "删除";
			cloneSql = cloneSql.substring(12);
			if(cloneSql.indexOf("|")!=-1){
				table = cloneSql.substring(0,cloneSql.indexOf("|"));
			}
			else{
				table = cloneSql;
			}
		}

		if("".equals(type)){
			return null;
		}

		stage.setExecType(type);
		stage.setExecTable(table);
		stage.setExecSql(sql);

		return stage;

	}

	public TbBackstage runSql(String sql,String commit){
		String driver = Global.getConfig("jdbc.driver");    //驱动标识符
		String url = Global.getConfig("jdbc.url");  // 连接远程的数据库可以这么写
		String username = Global.getConfig("jdbc.username");         //数据库的用户名
		String password = Global.getConfig("jdbc.password");     //数据库的密码
		Connection conn = null;
		Statement stmt = null;
		TbBackstage returnInfo = new TbBackstage();

		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			long affectNum = stmt.executeUpdate(sql);

			returnInfo.setAffectNum(affectNum);
		} catch(Exception e){
			returnInfo.setRemarks(e.getMessage());
			e.printStackTrace();
		} finally{
			// 关闭执行通道
			if(stmt !=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// 关闭连接通道
			try {
				if(conn != null &&(!conn.isClosed())) {
					try {
						if("0".equals(commit)) {
							conn.rollback();
						}
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return returnInfo;
	}
	
}