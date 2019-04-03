/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lw.common.web.BaseController;
import com.lw.modules.base.entity.TBaFile;
import com.lw.modules.base.service.TBaFileService;
import com.lw.modules.demo.service.PdfComposeService;

/**
 * 功能：PDF文件和图片的合成
 * 说明： 1、支持（PDF和PDF、PDF和图片、图片和图片）的合成
 *      2、图片的格式只能为（.jpg）格式
 *      3、合成出来的文件为PDF文件
 *      4、加密文件不支持合成
 * 创建时间：2016-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/PdfDemo")
public class PdfDemoController extends BaseController {
	
	@Autowired
	private PdfComposeService pdfComposeService;
	
	@Autowired
	private TBaFileService tBaFileService;
	
	@RequestMapping(value = "form")
	public String form() {
		return "modules/demo/pdfDemoForm";
	}	
	
	/**
	 * 功能：PDF的合成
	 * @param model
	 * @param request 
	 * @return
	 */
	@RequestMapping(value = "composePdf")
	public String composePdf(Model model,HttpServletRequest request) {
		// 1、获取页面的数据准备
		String fileIdOne = request.getParameter("fileIdOne");
		String fileIdTwo = request.getParameter("fileIdTwo");
		String fileIdThree = request.getParameter("fileIdThree");
		List<TBaFile> tBaFileList = new ArrayList<TBaFile>();
		tBaFileList.add(tBaFileService.get(fileIdOne)); 
		tBaFileList.add(tBaFileService.get(fileIdTwo));
		tBaFileList.add(tBaFileService.get(fileIdThree));
		
		// 2、调用合成的方法 （需要看详细代码）
		TBaFile resultPdf = pdfComposeService.createPublictyPdf(tBaFileList);
		
		// 3、合成后传递的参数
		model.addAttribute("fileIdOne", fileIdOne);
		model.addAttribute("fileIdTwo", fileIdTwo);
		model.addAttribute("fileIdThree", fileIdThree);
		model.addAttribute("resultPdf", resultPdf);
		return "modules/demo/pdfDemoForm";
	}	
}