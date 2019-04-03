package com.lw.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lw.common.config.Global;

/**
 * FTP服务器管理
 * @author handf
 * 创建时间：2016
 */
public class FTPUtil {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 连接FTP服务器
	 * @return 打开到根目录的FTP对象
	 */
	public FTPClient getFtpConnect(){
		//1、连接到FTP服务器上
        String url = Global.getConfig("FTP_URL");
        int port = Integer.parseInt(Global.getConfig("FTP_PORT"));
        String username = Global.getConfig("FTP_USERNAME");
        String password = Global.getConfig("FTP_PASSWORD"); 
        FTPClient ftp = new FTPClient();  
        try {
			ftp.connect(url,port);//连接FTP服务器  
            int reply;  
            ftp.login(username, password);//登录  
            ftp.enterLocalPassiveMode();//设置PassiveMode传输  
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);//设置以二进制流的方式传输    
            ftp.setFileType(FTP.BINARY_FILE_TYPE);  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
            	ftp.disconnect();  
            	logger.error("连接ftp服务器失败");   
            }
        } catch (Exception e) {
			e.printStackTrace();
		}
        return ftp;
	}
	
	/**
	 * 登出FTP
	 * @param ftp
	 */
	public void logoutFtp(FTPClient ftp){
		try {
			ftp.logout();
			if(ftp.isConnected()){
	        	ftp.disconnect();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开文件夹路径，如果没有该路径，进行创建
	 * @param folderPath 文件夹名称
	 * @param ftp
	 * @return
	 */
	public FTPClient openFolder(String folderPath, FTPClient ftp){
		String[] folderPaths = folderPath.split("/");
		boolean openFlag = true;
		if(folderPaths != null && folderPaths.length > 0){
			for (String TempFolderPath : folderPaths) {
				try{
					openFlag = ftp.changeWorkingDirectory(TempFolderPath);
			        if(openFlag == false && TempFolderPath != null && StringUtils.isNotBlank(TempFolderPath)){
			        	ftp.makeDirectory(TempFolderPath);
			        }
		        }catch(Exception e){
		        	logger.error("目录打开失败！");
		        	e.printStackTrace();
				}
			}
		}
		return ftp;
	}
}
