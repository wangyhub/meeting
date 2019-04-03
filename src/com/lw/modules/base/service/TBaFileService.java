/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lowagie.text.exceptions.BadPasswordException;
import com.lowagie.text.pdf.PdfReader;
import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.FTPUtil;
import com.lw.common.utils.FileDownUtil;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.dao.TBaFileDao;
import com.lw.modules.base.entity.TBaFile;
import com.lw.modules.sys.entity.User;
import com.lw.modules.sys.utils.UserUtils;

/**
 * 附件管理Service
 * @author handf
 * @version 2015-08-10
 */
@Service
@Transactional(readOnly = true)
public class TBaFileService extends CrudService<TBaFileDao, TBaFile> {

	public TBaFile get(String id) {
		return super.get(id);
	}
	
	public List<TBaFile> findList(TBaFile tBaFile) {
		return super.findList(tBaFile);
	}
	
	public Page<TBaFile> findPage(Page<TBaFile> page, TBaFile tBaFile) {
		return super.findPage(page, tBaFile);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaFile tBaFile) {
		super.save(tBaFile);
	}
	
	/**
	 * 删除文件数据 注：此处做的是假删除
	 */
	@Transactional(readOnly = false)
	public void delete(TBaFile tBaFile) {
		super.delete(tBaFile);
	}

	//----------------------------------------------------FTP文件上传和下载START
	/**
	 * 保存文件到FTP上
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveFTPFile(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
	    StringBuilder result = new StringBuilder("{");
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String name = request.getParameter("name");
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
	    
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
        if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            try {
                upload(file, tBaFile.getId() + fileType);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + UserUtils.getUser().getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        //上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	//上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append("}");
        return result.toString();
    }
	/**
	 * 保存文件到FTP上
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveFTPFile(TBaFile tBaFile, HttpServletRequest request,  MultipartFile file) {
	    StringBuilder result = new StringBuilder("{");
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	  
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
        if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            try {
                upload(file, tBaFile.getId() + fileType);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + UserUtils.getUser().getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        //上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	//上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append("}");
        return result.toString();
    }
	
	/**
	 * 保存文件到FTP上
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveFTPFileGz(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
	    StringBuilder result = new StringBuilder("{");
	    User user = UserUtils.getUser();
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String name = request.getParameter("name");
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
	    
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
        if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            try {
                upload(file, tBaFile.getId() + fileType);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + user.getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        
        String path = Global.getConfig("FTP_PATH"); // FTP的根路径
        FTPClient ftp = new FTPClient(); 
        TBaFile tBaFileSmall = new TBaFile();
        try {
        	FTPUtil ftpUtil = new FTPUtil();
	    	ftp = ftpUtil.getFtpConnect(); // 获得FTP服务器的连接
	    	ftp.changeWorkingDirectory(path + "/" + user.getId());//跳转到FTP相应的路径
	    	tBaFileSmall.preInsert();
	    	tBaFileSmall.setFileType(tBaFile.getFileType());
	    	
	        OutputStream out = ftp.appendFileStream(tBaFileSmall.getId() + tBaFileSmall.getFileType()); // FTP存储的文件名称   
	    	
	        float minSize = 35 * 1024 ;
	        float proportion = Float.parseFloat(tBaFile.getFileSize()) / minSize;
	        
	        tBaFileSmall.setFileSize(Math.rint(Float.parseFloat(tBaFile.getFileSize()) * proportion) + "");
	        InputStream input = file.getInputStream();
	        Thumbnails.of(input).scale(1f).outputQuality(proportion).toOutputStream(out);
		    
			tBaFileSmall.setFileType(fileType);                     // 文件类型
			tBaFileSmall.setFileName(fileName.replace(" ", ""));    // 原文件名
			tBaFileSmall.setFolderPath("/" + user.getId());    // 文件夹路径
			dao.insert(tBaFileSmall);
			
			input.close();  
			out.close();
	        ftpUtil.logoutFtp(ftp);
			
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        // 上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	// 上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append(",");
    	// 上传成功后的小文件
    	result = contactJson(result, "smallFileId", tBaFileSmall.getId());
    	result.append("}");
        return result.toString();
    }
	
    /**  
     * 上传文件到FTP服务器上 
     * @param file 上传的文件 
     * @param filename 文件的名称
     * @throws Exception
     */
    public void upload(MultipartFile file, String filename) throws Exception{  
    	String path = Global.getConfig("FTP_PATH");
        FTPUtil ftpUtil = new FTPUtil();
        FTPClient ftp = ftpUtil.getFtpConnect();
        String userId = UserUtils.getUser().getId();
        boolean openRoot = ftp.changeWorkingDirectory(path);// 打开一级目录
        try{
	        if(openRoot == true){
	        	boolean openSecond = ftp.changeWorkingDirectory(userId);
	        	if(openSecond == false){
	        		ftp.makeDirectory(userId);
	        		ftp.changeWorkingDirectory(userId);
	        	}
	        }else {
	        	logger.error("FTP服务器根目录打开失败！");
	        }
        }catch(Exception ex){
        	logger.error("创建二级文件夹失败！");
		}
			
        //2、上传文件
        InputStream input = file.getInputStream();        
        ftp.storeFile(filename, input);      
        input.close();  
        ftpUtil.logoutFtp(ftp);
    }    
	
	/**
	 * FTP服务器附件的下载
	 * @param request
	 * @param response
	 */
	public void downFTPFile(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response) {
	    try {
            downFTP(tBaFile, response, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**  
     * 下载FTP服务器上的文件
     * @param file 上传的文件
     * @throws Exception  
     */    
    public void downFTP(TBaFile tBaFile, HttpServletResponse response, HttpServletRequest request) throws Exception{
    	String path = Global.getConfig("FTP_PATH");
        FTPUtil ftpUtil = new FTPUtil();
        FTPClient ftp = ftpUtil.getFtpConnect();
        //2、下载文件
        String folderPath = tBaFile.getFolderPath();
        boolean isChangeWork = true;
        if(tBaFile.getFolderPath() != null && StringUtils.isNotBlank(folderPath)){
        	ftp.changeWorkingDirectory(path + tBaFile.getFolderPath());  
        }else {
        	ftp.changeWorkingDirectory(path); 
        }
        if (!isChangeWork) {  
            throw new IOException("ftp 目录不存在");  
        } 
        response.reset();//清空response
        response.setCharacterEncoding("UTF-8"); 
        //名称两边的双引号不能省略 兼容火狐 文件名中的空格
        response.addHeader("Content-Disposition", "attachment;filename=\""
                + FileDownUtil.encodeFilename(request, tBaFile.getFileName()) + "\"");
        response.addHeader("Content-Length", "" + tBaFile.getFileSize());
        InputStream input = ftp.retrieveFileStream(tBaFile.getId() + tBaFile.getFileType());
        OutputStream out = response.getOutputStream();
        int buf = -1;  
        while ((buf = input.read()) != -1) {  
            out.write(buf);  
        }  
        out.flush();  
        input.close();  
        ftpUtil.logoutFtp(ftp);
    } 
    
    /**
     * 查询信息集合
     * @param request
     * @return
     */
	@Transactional(readOnly = false)
	public List<TBaFile> queryFile(HttpServletRequest request) {
	    String value = request.getParameter("value");
	    String[] fileIds = value.split(",");
	    List<String> list = new ArrayList<String>();
	    for(int i = 0; i < fileIds.length; i++){
	        list.add(fileIds[i]);
	    }
	    Map<String, List<String>> condition = new HashMap<String, List<String>>();
	    condition.put("fileIds", list);
	    List<TBaFile> fileList = new ArrayList<TBaFile>();
	    List<TBaFile> files = dao.queryFile(condition);
	    if(null != files){
	        for (TBaFile tBaFile : files) {
	        	String sizeLabel = showSizeLabel(tBaFile.getFileSize());
	            tBaFile.setFileSize(sizeLabel);
	            fileList.add(tBaFile);
	        }
	    }
	    return fileList;
	}
    
	//----------------------------------------------------FTP文件上传和下载END
    
	//----------------------------------------------------服务器的文件上传和下载START
	
	/**
	 * 保存文件到项目服务器
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveLocationFile(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
	    StringBuilder result = new StringBuilder("{");
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String name = request.getParameter("name");
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
	    
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	    if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
	    if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            String path = Global.getConfig("DEFINE_ATTACHMENT") ;//上传路径
            User user = UserUtils.getUser();
            if(user != null && StringUtils.isNotBlank(user.getId())){
            	path = path + File.separator + user.getId();
            }
            // 注：该配置在config.properties文件里。
            //在windows系统配置下，双斜杠（例如：DEFINE_ATTACHMENT=D\:\\temp\\）。
            //linicx系统，单斜杠（例如：#DEFINE_ATTACHMENT=opt/attachment/define/）。
            try {
            	uploadLocation(file, tBaFile.getId() + fileType, path);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + UserUtils.getUser().getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        //上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	//上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append("}");
        return result.toString();
    }
	@Transactional(readOnly = false)
	public String saveLocationFile(TBaFile tBaFile, HttpServletRequest request,  MultipartFile file ) {
	    StringBuilder result = new StringBuilder("{");
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	    if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
	    if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            String path = Global.getConfig("DEFINE_ATTACHMENT") ;//上传路径
            User user = UserUtils.getUser();
            if(user != null && StringUtils.isNotBlank(user.getId())){
            	path = path + File.separator + user.getId();
            }
            // 注：该配置在config.properties文件里。
            //在windows系统配置下，双斜杠（例如：DEFINE_ATTACHMENT=D\:\\temp\\）。
            //linicx系统，单斜杠（例如：#DEFINE_ATTACHMENT=opt/attachment/define/）。
            try {
            	uploadLocation(file, tBaFile.getId() + fileType, path);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + UserUtils.getUser().getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        //上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	//上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append("}");
        return result.toString();
    }
	
	/**  
     * 上传文件
     * @param file 上传的文件
     * @throws Exception  
     */    
    public void uploadLocation(MultipartFile file, String filename, String path) throws Exception{  
    	if (!file.isEmpty()) {  
	        File fs = new File(path);
			if(!fs.exists()){
				fs.mkdir();
			}
	        if(!file.isEmpty()){
	            try {
	                FileOutputStream fos = new FileOutputStream(path + File.separator + filename);
	                InputStream in = file.getInputStream();
	                int b = 0;
	                while((b=in.read())!=-1){
	                    fos.write(b);
	                }
	                fos.close();
	                in.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
    	}
    }   
	
	//----------------------------------------------------服务器的文件上传和下载END
	
    //----------------------------------------------------FTP文件图片上传和下载START
    /**
     * 获取展示FTP服务器上的图片
     * @param file 上传的文件
     * @throws Exception
     */
    public void showFTPImage(TBaFile tBaFile, HttpServletResponse response,
        HttpServletRequest request){
        // 1、连接到FTP服务器上
    	String path = Global.getConfig("FTP_PATH");
        FTPUtil ftpUtil = new FTPUtil();
        FTPClient ftp = ftpUtil.getFtpConnect();
        // 2、下载文件
        String folderPath = "";
        if(StringUtils.isNotBlank(tBaFile.getFolderPath())){
        	folderPath = tBaFile.getFolderPath();
        }
        boolean isChangeWork;
		try {
			isChangeWork = ftp.changeWorkingDirectory(path + folderPath);
			if (!isChangeWork) {
	            throw new IOException("ftp 目录不存在");
	        }
			// 清空response
	        response.reset();
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("image/*"); // 设置返回的文件类型
	        if (tBaFile.getFileName() != null && !"".equals(tBaFile.getFileName())) {
	            response.addHeader("Content-Disposition", "attachment;filename=\""
	                + FileDownUtil.encodeFilename(request, tBaFile.getFileName())
	                + "\"");// 名称两边的双引号不能省略 兼容火狐 文件名中的空格
	            // response.addHeader("Content-Length", "" + tBaFile.getFileSize());
	            ServletOutputStream out = response.getOutputStream();
	            InputStream input = ftp.retrieveFileStream(tBaFile.getId()
	                + tBaFile.getFileType());
	            byte[] b = new byte[1024 * 1024];
	            int len;
	            while ((len = input.read(b)) > 0)
	                out.write(b, 0, len);
	            out.flush();
	            out.close();
	            input.close();
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ftpUtil.logoutFtp(ftp);
    }
    
    /**
     * 获取展示项目服务器上的图片
     * @param file 上传的文件
     * @throws Exception
     */
    public HttpServletResponse showLocationImage(TBaFile tBaFile, String path, HttpServletResponse response,
        HttpServletRequest request){
    	try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
                if(!file.exists()) {  
                    response.setContentType("text/html;charset=GBK"); 
                    response.getWriter().print("指定文件不存在！");        
                    return response;           
            }
            // 清空response
            response.reset();
            response.setCharacterEncoding("UTF-8"); 
            // 设置response的Header
            // realname = URLEncoder.encode(realname, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=\""+ FileDownUtil.encodeFilename(request,tBaFile.getFileName())+"\"");//名称两边的双引号不能省略 兼容火狐 文件名中的空格
            response.addHeader("Content-Length", "" + file.length());
            ServletOutputStream out = response.getOutputStream(); 
            InputStream input = new FileInputStream(file); 
            byte[] b = new byte[1024 * 1024];
            int len;
            while ((len = input.read(b)) > 0)
                out.write(b, 0, len);
            out.flush();
            out.close();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
    //----------------------------------------------------FTP文件图片上传和下载END
    
	/**
	 * 静态附件的下载
	 * @param fileName 传递文件名
	 * @param request
	 * @param response
	 */
	public void downStaticFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
	    if("drys.xlsx".equals(fileName)){
	        fileName = "导入演示.xlsx";
	    }
	    if("budgetInfo.xlsx".equals(fileName)){
	        fileName = "预算导入模板.xlsx";
	    }
	    String path = request.getSession().getServletContext().getRealPath("")+"/static/files/"+fileName;
	    downloadLocationFile(fileName,path,request,response);
	}	
	
	/**
	 * 下载附件响应方法。
	 * @param realname
	 * @param path
	 * @param request
	 * @param response
	 * @return
	 */
	public HttpServletResponse downloadLocationFile(String realname,String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
                if(!file.exists()) {  
                    response.setContentType("text/html;charset=GBK"); 
                    response.getWriter().print("指定文件不存在！");        
                    return response;           
            }
            // 清空response
            response.reset();
            response.setCharacterEncoding("UTF-8"); 
            // 设置response的Header
            // realname = URLEncoder.encode(realname, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=\""+ FileDownUtil.encodeFilename(request,realname)+"\"");//名称两边的双引号不能省略 兼容火狐 文件名中的空格
            response.addHeader("Content-Length", "" + file.length());
            ServletOutputStream out = response.getOutputStream(); 
            InputStream inStream=new FileInputStream(file); 
            byte[] b = new byte[1024]; 
            int len; 
            while((len=inStream.read(b)) >0) 
            out.write(b,0,len); 
            out.flush();
            out.close();
            inStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	
	//----------------------------------------------------封装的工具类START
	
	/**
	 * 拼接JSON格式字符
	 * @param sb 字符容器
	 * @param key 键
	 * @param value 值
	 * @return
	 */
    public StringBuilder contactJson(StringBuilder sb, String key, String value){
    	sb.append("\"");
    	sb.append(key);
    	sb.append("\"");
    	sb.append(":" );
    	sb.append("\"");
    	sb.append(value);
    	sb.append("\"");
    	return sb;
    }
    
    /**
     * 展现文件的大小
     * @param fileSize
     * @return
     */
    public String showSizeLabel(String fileSize){
    	String sizeLabel = "";
        String sizeLevel = "B";
        double tempFile = 0;
        if(StringUtils.isNotBlank(fileSize)){
        	tempFile = Double.parseDouble(fileSize);
        }
        if(tempFile > 1024){
            tempFile = tempFile / 1024.0;
            sizeLevel = "K";
        }
        if(tempFile > 1024){
            tempFile = tempFile / 1024;
            sizeLevel = "M";
        }
        DecimalFormat df = new DecimalFormat("######0.00"); 
        sizeLabel = df.format(tempFile);
    	return sizeLabel + sizeLevel;
    }
    
    //----------------------------------------------------封装的工具类END
}