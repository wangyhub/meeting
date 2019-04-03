/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.utils.StringUtils;
import com.lw.common.web.BaseController;
import com.lw.modules.demo.entity.TBzExpertExcelDemo;
import com.lw.modules.demo.service.TBzExpertExcelDemoService;

/**
 * Excel演示Controller
 * @author handf
 * @version 2016-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/tBzExpertExcelDemo")
public class TBzExpertExcelDemoController extends BaseController {

	@Autowired
	private TBzExpertExcelDemoService tBzExpertExcelDemoService;
	
	@ModelAttribute
	public TBzExpertExcelDemo get(@RequestParam(required=false) String id) {
		TBzExpertExcelDemo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBzExpertExcelDemoService.get(id);
		}
		if (entity == null){
			entity = new TBzExpertExcelDemo();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TBzExpertExcelDemo tBzExpertExcelDemo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBzExpertExcelDemo> page = tBzExpertExcelDemoService.findPage(new Page<TBzExpertExcelDemo>(request, response), tBzExpertExcelDemo); 
		model.addAttribute("page", page);
		return "modules/demo/tBzExpertExcelDemoList";
	}

	@RequestMapping(value = "form")
	public String form(TBzExpertExcelDemo tBzExpertExcelDemo, Model model) {
		model.addAttribute("tBzExpertExcelDemo", tBzExpertExcelDemo);
		return "modules/demo/tBzExpertExcelDemoForm";
	}

	@RequestMapping(value = "save")
	public String save(TBzExpertExcelDemo tBzExpertExcelDemo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBzExpertExcelDemo)){
			return form(tBzExpertExcelDemo, model);
		}
		tBzExpertExcelDemoService.save(tBzExpertExcelDemo);
		addMessage(redirectAttributes, "保存Excel演示成功");
		return "redirect:"+Global.getAdminPath()+"/demo/tBzExpertExcelDemo/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TBzExpertExcelDemo tBzExpertExcelDemo, RedirectAttributes redirectAttributes) {
		tBzExpertExcelDemoService.delete(tBzExpertExcelDemo);
		addMessage(redirectAttributes, "删除Excel演示成功");
		return "redirect:"+Global.getAdminPath()+"/demo/tBzExpertExcelDemo/?repage";
	}
	/**
	 * excel保存导入
	 * @param tBzExpertExcelDemo
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@RequestMapping(value = "excelSave", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject excelSave(TBzExpertExcelDemo tBzExpertExcelDemo, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("fileUpload");// 获得上传的附件
		JSONObject json = tBzExpertExcelDemoService.excelSave(multipartFile, request);
		return json;
	}
	
	/**
	 * 导出模拟信息表
	 * @param tBzExpertExcelDemo
	 * @param request
	 * @param response
	 * @param model
	 */
    @RequestMapping(value = "exportExcel")
    public void exportExcel(TBzExpertExcelDemo tBzExpertExcelDemo, HttpServletRequest request, HttpServletResponse response, Model model){
    	List<TBzExpertExcelDemo> tBzExpertExcelDemos = tBzExpertExcelDemoService.findList(tBzExpertExcelDemo);
		
    	String fileName = "导出模拟信息表.xls";
        try{
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("导出模拟信息表");
            HSSFRow row = sheet.createRow((int) 0);
            HSSFCellStyle style = wb.createCellStyle(); // 设置表头样式
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
            String[] excelHeader = { "序号", "列一", "列二", "列三"}; // excel表头
            for (int i = 0; i < excelHeader.length; i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(style);
                sheet.setColumnWidth(i, 4000);
            }
            if(tBzExpertExcelDemos != null && tBzExpertExcelDemos.size() > 0){
	            for (int i = 0; i < tBzExpertExcelDemos.size(); i++){
	            	TBzExpertExcelDemo expertExcelDemo = tBzExpertExcelDemos.get(i);
	                row = sheet.createRow(i + 1);
	                row.createCell(0).setCellValue(i + 1);
	                row.createCell(1).setCellValue(expertExcelDemo.getColumnOne());
	                row.createCell(2).setCellValue(expertExcelDemo.getColumnTwo());
	                row.createCell(3).setCellValue(expertExcelDemo.getColumnThree());
	            }
            }
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            wb.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}