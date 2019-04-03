/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.base.web;


import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lw.common.config.Global;
import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.base.entity.TBaFile;
import com.lw.modules.base.service.TBaFileService;

/**
 * 功能：附件管理Controller
 * 说明： 1、实现FTP文件标签的增删改查
 *     2、实现本地文件的增删改查
 *     3、实现FTP图片文件的增删改查
 * @author handf
 * @version 2015-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tBaFile")
public class TBaFileController extends BaseController {

	@Autowired
	private TBaFileService tBaFileService;
	
	@ModelAttribute
	public TBaFile get(@RequestParam(required=false) String id) {
		TBaFile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBaFileService.get(id);
		}
		if (entity == null){
			entity = new TBaFile();
		}
		return entity;
	}
	
	/**
     * 功能：保存文件到FTP服务器/项目服务器上
     * @param tBaFile
     * @param request
     * @param response
     * @param multipartHttpServletRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response,
            MultipartHttpServletRequest multipartHttpServletRequest) {
    	String defineUpload = Global.getConfig("DEFINE_UPLOAD"); // true FTP服务器  false 项目服务器
    	String result = "";
    	if("true".equals(defineUpload)){ // FTP服务器
    		result = tBaFileService.saveFTPFile(tBaFile, request, multipartHttpServletRequest);
    	}else { // 项目服务器
    		result = tBaFileService.saveLocationFile(tBaFile, request, multipartHttpServletRequest);
    	}
    	
        return result;
    }
    
	/**
     * 功能：保存文件到FTP服务器/项目服务器上
     * @param tBaFile
     * @param request
     * @param response
     * @param multipartHttpServletRequest
     * @return
     */
    @RequestMapping(value = "saveGz", method = RequestMethod.POST)
    @ResponseBody
    public String saveGz(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response,
            MultipartHttpServletRequest multipartHttpServletRequest) {
    	String result = "";
        result = tBaFileService.saveFTPFileGz(tBaFile, request, multipartHttpServletRequest);
        return result;
    }
    
    
	
	/**
     * 下载FTP服务器的附件
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "downFile")
    public void downFile(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response) {
    	String defineUpload = Global.getConfig("DEFINE_UPLOAD"); // true FTP服务器  false 项目服务器
    	if("true".equals(defineUpload)){ 
    		tBaFileService.downFTPFile(tBaFile, request, response);
    	}else {
    		String path = Global.getConfig("DEFINE_ATTACHMENT") +  tBaFile.getFolderPath() + File.separator + tBaFile.getId() + tBaFile.getFileType();
    		tBaFileService.downloadLocationFile(tBaFile.getFileName(), path, request, response);
    	}
    }

    /**
     * 删除FTP服务器/项目服务器指定文件
     * @param tBaFile
     * @param request
     */
    @RequestMapping(value = "deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public void deleteFile(TBaFile tBaFile,  HttpServletRequest request) {
        String fileId = request.getParameter("fileId");// 文件编号
        tBaFile.setId(fileId);
        tBaFileService.delete(tBaFile);
    }
    
    /**
     * 查询文件信息集合
     * @param tBaFile
     * @param request
     * @return
     */
    @RequestMapping(value = "queryFile", method = RequestMethod.POST)
    @ResponseBody
    public List<TBaFile> queryFile(TBaFile tBaFile,  HttpServletRequest request) {
        List<TBaFile> list = tBaFileService.queryFile(request);
        return list;
    }
    
    /**
     * 查询单个文件信息
     * @param tBaFile
     * @param request
     * @return
     */
    @RequestMapping(value = "queryOneFile", method = RequestMethod.POST)
    @ResponseBody
    public TBaFile queryOneFile(String value,  HttpServletRequest request) {
    	TBaFile baFile = tBaFileService.get(value);
        return baFile;
    }
    
    //----------------------------------------------------FTP文件图片上传和下载START
    
    /**
     * 下载图片文件
     * @param tBaFile
     * @param request
     * @param response
     */
    @RequestMapping(value = "showImage")
    public void showImage(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response) {
    	String defineUpload = Global.getConfig("DEFINE_UPLOAD"); // true FTP服务器  false 项目服务器
    	if("true".equals(defineUpload)){ 
    		tBaFileService.showFTPImage(tBaFile, response, request);
    	}else {
    		String path = Global.getConfig("DEFINE_ATTACHMENT") +  tBaFile.getFolderPath() + File.separator + tBaFile.getId() + tBaFile.getFileType();
    		tBaFileService.showLocationImage(tBaFile, path, response, request);
    	}
    }
    
    /**
     * 展示图片文件
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "showImaged")
    public String showImaged(HttpServletRequest request, HttpServletResponse response, Model model) {
        String attFileId = request.getParameter("attFileId");
        model.addAttribute("attFileId", attFileId);
        return "modules/base/showImage";
    } 
    
    //----------------------------------------------------FTP文件图片上传和下载END
    
    /**
     * 下载静态文件附件
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "downStaticFile")
    public void downStaticFile(String name, HttpServletRequest request, HttpServletResponse response) {     
        try {
           //name=new String(request.getParameter("name").getBytes("iso-8859-1"),"");
           name=request.getParameter("name"); // 传递的文件名
        } catch (Exception e) {
            e.printStackTrace();
        }       
        tBaFileService.downStaticFile(name, request, response);
    }  

    
}