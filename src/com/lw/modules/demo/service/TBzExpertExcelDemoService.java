/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.demo.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.StringUtils;
import com.lw.common.utils.excel.ExcelUtil;
import com.lw.modules.demo.dao.TBzExpertExcelDemoDao;
import com.lw.modules.demo.entity.TBzExpertExcelDemo;

/**
 * Excel演示Service
 * @author handf
 * @version 2016-08-26
 */
@Service
@Transactional(readOnly = true)
public class TBzExpertExcelDemoService extends CrudService<TBzExpertExcelDemoDao, TBzExpertExcelDemo> {

	public TBzExpertExcelDemo get(String id) {
		return super.get(id);
	}
	
	public List<TBzExpertExcelDemo> findList(TBzExpertExcelDemo tBzExpertExcelDemo) {
		return super.findList(tBzExpertExcelDemo);
	}
	
	public Page<TBzExpertExcelDemo> findPage(Page<TBzExpertExcelDemo> page, TBzExpertExcelDemo tBzExpertExcelDemo) {
		return super.findPage(page, tBzExpertExcelDemo);
	}
	
	@Transactional(readOnly = false)
	public void save(TBzExpertExcelDemo tBzExpertExcelDemo) {
		super.save(tBzExpertExcelDemo);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBzExpertExcelDemo tBzExpertExcelDemo) {
		super.delete(tBzExpertExcelDemo);
	}
	
	
	/**
	 * 导入Excel
	 * @param multipartFile
	 * @param request
	 */
	@Transactional(readOnly = false)
	public JSONObject excelSave(MultipartFile multipartFile, HttpServletRequest request){
		JSONObject json = new JSONObject();// 返回的JOSN1、成功标志isSuccess 1 导入失败  2  导入成功
		List<TBzExpertExcelDemo> tBzExpertExcelDemos = new ArrayList<TBzExpertExcelDemo>();
		try {
			// 1、检验SHEET页是否正确
			InputStream is = multipartFile.getInputStream();// 获取文件流
			XSSFWorkbook wb = new XSSFWorkbook(is);// 转化工作簿
			JSONArray errorArray = new JSONArray();// 放置校验不通过信息
			ExcelUtil.removeComment(wb, 2);// 指定行取出边框，注释，样式
			XSSFSheet sheet = wb.getSheet("导入示例");// 获取评价SHEET
			if (sheet == null) {// 评价计划SHEET不存在，返回消息提示
				json.put("isSuccess", "01");
				json.put("errorMsg", "导入示例SHEET页不存在，请下载标准模板填写数据！");
			}
			
			//2、判断表头是否正确
			if (sheet.getRow(0).getCell(0) == null || !"列一".equals(sheet.getRow(0).getCell(0).getStringCellValue().trim())) {
				json.put("isSuccess", "01");
				json.put("errorMsg","导入示例SHEET页中第1行第1列应该为“列一”，请下载标准模板填写数据！");
				return json;
			} 
			
			if (sheet.getRow(0).getCell(1) == null || !"列二".equals(sheet.getRow(0).getCell(1).getStringCellValue().trim())) {
				json.put("isSuccess", "01");
				json.put("errorMsg","导入示例SHEET页中第1行第2列应该为“列二”，请下载标准模板填写数据！");
				return json;
			} 
			
			if (sheet.getRow(0).getCell(2) == null || !"列三".equals(sheet.getRow(0).getCell(2).getStringCellValue().trim())) {
				json.put("isSuccess", "01");
				json.put("errorMsg","导入示例SHEET页中第1行第3列应该为“列三”，请下载标准模板填写数据！");
				return json;
			} 
			
			// 3、数据为空,返回消息提示
			if (sheet.getLastRowNum() < 1) {
				json.put("isSuccess", "01");
				json.put("errorMsg", "导入示例SHEET页中数据为空，请填写数据！");
				return json;
			}
			
			// 4、对导入的数据做校验
			String columnOne = ""; // 列一
			String columnTwo = ""; // 列二
			String columnThree = ""; // 列三
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					// 4.1、获得单元格的值
					columnOne = getCellStringValues(sheet, i, 0);
					columnTwo = getCellStringValues(sheet, i, 1);
					columnThree = getCellStringValues(sheet, i, 2);
					// 4.2、一行全部为空忽略
					if (i > 1) {
						if (StringUtils.isBlank(columnOne)
								&& StringUtils.isBlank(columnTwo)
								&& StringUtils.isBlank(columnThree)) {
							continue;
						}
					}
					
					// 4.3、进行逐条信息校验
					// 列一
					if(StringUtils.isBlank(columnOne)){
						JSONObject error = addErrorInfo(sheet.getSheetName(), i, 0, (i + 1) + "行1列中“指标类别”不能为空");
						errorArray.add(error);
						ExcelUtil.addSingleCellColorBorder(wb, sheet, i, 0);// 添加边框
						ExcelUtil.addComment(sheet, i, 0, "“列一”不能为空");// 添加注释
					}else if(!lengthValidator(columnOne, 1, 400)){
						JSONObject error = addErrorInfo(sheet.getSheetName(), i, 0, (i + 1)+ "行1列中“指标类别”长度不能超过400个字符");
						errorArray.add(error);
						ExcelUtil.addSingleCellColorBorder(wb, sheet, i, 0);// 添加边框
						ExcelUtil.addComment(sheet, i, 0, "“列一”长度不能超过400个字符");// 添加注释
					}
					// 列二
					if(StringUtils.isBlank(columnTwo)){
						JSONObject error = addErrorInfo(sheet.getSheetName(), i, 1, (i + 1) + "行2列中“分指标内容”不能为空");
						errorArray.add(error);
						ExcelUtil.addSingleCellColorBorder(wb, sheet, i, 1);// 添加边框
						ExcelUtil.addComment(sheet, i, 1, "“列二”不能为空");// 添加注释
					}else if(!lengthValidator(columnTwo, 1, 1000)){
						JSONObject error = addErrorInfo(sheet.getSheetName(), i, 1, (i + 1)+ "行2列中“分指标内容”长度不能超过1000个字符");
						errorArray.add(error);
						ExcelUtil.addSingleCellColorBorder(wb, sheet, i, 1);// 添加边框
						ExcelUtil.addComment(sheet, i, 1, "“列二”长度不能超过1000个字符");// 添加注释
					}
					// 列三
					if(StringUtils.isBlank(columnThree)){
						JSONObject error = addErrorInfo(sheet.getSheetName(), i, 2, (i + 1) + "行3列中“评价结果”不能为空");
						errorArray.add(error);
						ExcelUtil.addSingleCellColorBorder(wb, sheet, i, 2);// 添加边框
						ExcelUtil.addComment(sheet, i, 2, "“列三”不能为空");// 添加注释
					}else if(!lengthValidator(columnThree, 1, 300)){
						JSONObject error = addErrorInfo(sheet.getSheetName(), i, 2, (i + 1)+ "行3列中“评价结果”长度不能超过300个字符");
						errorArray.add(error);
						ExcelUtil.addSingleCellColorBorder(wb, sheet, i, 2);// 添加边框
						ExcelUtil.addComment(sheet, i, 2, "“列三”长度不能超过300个字符");// 添加注释
					}

					// 重复性校验 
					
					// 4.4、校验通过
					if (errorArray == null || errorArray.size() == 0) {
						TBzExpertExcelDemo tBzExpertExcelDemo = new TBzExpertExcelDemo();
						tBzExpertExcelDemo.setColumnOne(columnOne);
						tBzExpertExcelDemo.setColumnTwo(columnTwo);
						tBzExpertExcelDemo.setColumnThree(columnThree);
						tBzExpertExcelDemo.setSort(i + "");
						tBzExpertExcelDemos.add(tBzExpertExcelDemo);
					}
				}
			}
			
			// 如果校验信息和人员数据都为空，也属于数据为空(列表中全是空行)
			if ((errorArray == null || errorArray.size() == 0) && (tBzExpertExcelDemos == null || tBzExpertExcelDemos.size() == 0)) {
				json.put("isSuccess", "01");
				json.put("errorMsg", "导出演示SHEET页中数据为空，请填写数据！");
				return json;
			}
			
			if (errorArray != null && errorArray.size() > 0) {
				json.put("isSuccess", "01");
				json.put("msgArray", errorArray);
				return json;
			}
			
			// 5、如果没有错误就保存到信息表
			// 清除表信息
			dao.deleteAll();
			
			if(tBzExpertExcelDemos != null && tBzExpertExcelDemos.size() > 0){
				for (TBzExpertExcelDemo tBzExpertExcelDemo : tBzExpertExcelDemos) {
					tBzExpertExcelDemo.preInsert();
					dao.insert(tBzExpertExcelDemo);
				}
			}

			json.put("isSuccess", "02");
			
		} catch (Exception e) {
		    // TODO: handle exception
		}
		return json;
	}
	
	/**
	 * 错误信息map
	 * @param sheetName
	 * @param row
	 * @param col
	 * @param msg
	 * @return
	 */
	private JSONObject addErrorInfo(String sheetName, int row, int col,String msg) {
		JSONObject errorJson = new JSONObject();
		errorJson.put("sheetName", sheetName);
		errorJson.put("rowIndex", row + 1);
		errorJson.put("colIndex", col);
		errorJson.put("errorMsg", msg);
		return errorJson;
	}
	
	
	/**
	 * 获得单元格的值（包括合并的单元格）
	 * @param indexI
	 * @param row
	 * @return
	 */
	private String getCellStringValues(XSSFSheet sheet, int rowNumber, int  columnNumber) {
		String tempString  = "";
		XSSFRow row = sheet.getRow(rowNumber);
		boolean mergedFlag = isMergedRegion(sheet, rowNumber, columnNumber);
		if(mergedFlag){
			tempString = getMergedRegionValue(sheet, rowNumber, columnNumber);
		}else {
			tempString = getCellStringValue(columnNumber, row);
		}
		return tempString;
	}
	
	/**
	 * 获得单个单元格的文字
	 * @param indexI
	 * @param row
	 * @return
	 */
	private String getCellStringValue(int indexI, XSSFRow row) {
		String tempString  = "";
		XSSFCell tempCell = row.getCell(indexI) != null ? row.getCell(indexI): row.createCell(indexI);// 指标类别
		if (tempCell != null) {
			tempCell.setCellType(XSSFCell.CELL_TYPE_STRING);// 设置字符串类型，避免出错
			tempString = tempCell.getStringCellValue().trim();
		}
		return tempString;
	}
	
	 /**   
     * 获取合并单元格的值   
     * @param sheet   
     * @param row   
     * @param column   
     * @return   
     */    
     public String getMergedRegionValue(XSSFSheet sheet ,int row , int column){    
         int sheetMergeCount = sheet.getNumMergedRegions();    
         String tempString = "";
         for(int i = 0 ; i < sheetMergeCount ; i++){    
             CellRangeAddress ca = sheet.getMergedRegion(i);    
             int firstColumn = ca.getFirstColumn();    
             int lastColumn = ca.getLastColumn();    
             int firstRow = ca.getFirstRow();    
             int lastRow = ca.getLastRow();    
             if(row >= firstRow && row <= lastRow){    
                  if(column >= firstColumn && column <= lastColumn){    
                	 XSSFRow fRow = sheet.getRow(firstRow);
                	 XSSFCell fCell = fRow.getCell(firstColumn);
                	 if (fCell != null) {
                		 fCell.setCellType(XSSFCell.CELL_TYPE_STRING);// 设置字符串类型，避免出错
                		 tempString = fCell.getStringCellValue().trim();
             		}
                     return tempString;
                 }    
             }    
         }    
             
         return tempString ;    
     }   
	
	/**
	 * 长度校验
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	private boolean lengthValidator(String strValue, int min, int max) {
		boolean isValidate = true;
		int length = ExcelUtil.getWordCount(strValue);
		if (length < min || length > max) {
			isValidate = false;
		}
		return isValidate;
	}
	
	 /**  
     * 判断指定的单元格是否是合并单元格  
     * @param sheet   
     * @param row 行下标  
     * @param column 列下标  
     * @return  
     */  
	private boolean isMergedRegion(XSSFSheet sheet,int row ,int column) {  
       int sheetMergeCount = sheet.getNumMergedRegions();  
       for (int i = 0; i < sheetMergeCount; i++) {  
             CellRangeAddress range = sheet.getMergedRegion(i);  
             int firstColumn = range.getFirstColumn();
             int lastColumn = range.getLastColumn();  
             int firstRow = range.getFirstRow();  
             int lastRow = range.getLastRow();  
             if(row >= firstRow && row <= lastRow){
                 if(column >= firstColumn && column <= lastColumn){
                     return true;  
                 }
             }  
       }
       return false;  
     } 
}