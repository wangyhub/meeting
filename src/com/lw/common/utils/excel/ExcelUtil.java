package com.lw.common.utils.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel工具类
 * 
 * @author邓纯杰
 */
public class ExcelUtil {
	// 边框粗细，如果不设置则无边框
	private static short BORDER_THIN = XSSFCellStyle.BORDER_THIN;

	private static short BORDER_MEDIUM = XSSFCellStyle.BORDER_MEDIUM;

	// 测试过都为红色
	private static XSSFColor XSSFCOLOR_RED = new XSSFColor(java.awt.Color.RED);

	private static XSSFColor XSSFCOLOR_BLACK = new XSSFColor(java.awt.Color.BLACK);

	private static int color = 2;

	private static final DataFormatter FORMATTER = new DataFormatter();
	
	public static Map<XSSFWorkbook,XSSFCellStyle> styles=new HashMap<XSSFWorkbook,XSSFCellStyle>();
	

	/**
	 * 获取单元格内容
	 * 
	 * @param cell
	 *            单元格对象
	 * @return 转化为字符串的单元格内容
	 */
	private static String getCellContent(Cell cell) {
		return FORMATTER.formatCellValue(cell);
	}

	public static String getMergedRegionValue(Sheet sheet, Cell cell) {
		// 获得一个 sheet 中合并单元格的数量
		int sheetmergerCount = sheet.getNumMergedRegions();
		// 便利合并单元格
		for (int i = 0; i < sheetmergerCount; i++) {
			// 获得合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 获得合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int firstR = ca.getFirstRow();
			if (cell.getColumnIndex() == firstC && cell.getRowIndex() == firstR) {
				return "第" + (cell.getRowIndex() + 1) + "行 第"
						+ (cell.getColumnIndex() + 1) + "列 的内容是： "
						+ getCellContent(cell) + ",";
			}
		}
		return "";
	}

	/*
	 * @return XSSFCell ，如果cell在合并单元格内，则返回相应合并单元格的第一行，第一列的cell
	 */
	public static XSSFCell getMergedRegionValue(XSSFSheet sheet, int rowIndex,
			int columnIndex) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			org.apache.poi.ss.util.CellRangeAddress ca = sheet
					.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (rowIndex >= firstRow && rowIndex <= lastRow) {
				if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
					XSSFRow fRow = sheet.getRow(firstRow);
					XSSFCell fCell = fRow.getCell(firstColumn);
					return fCell;
				}
			}
		}
		return null;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param cell
	 * @return
	 */
	public static boolean isMergedRegion(Sheet sheet, Cell cell) {
		// 得到一个sheet中有多少个合并单元格
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
			int lastR = ca.getLastRow();
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (cell.getColumnIndex() <= lastC
					&& cell.getColumnIndex() >= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<Integer> getMergedNum(XSSFSheet sheet, Integer columIndex) {
		int sheetmergerCount = sheet.getNumMergedRegions();
		List<Integer> mergerdNum = new ArrayList<Integer>();
		List<Integer> lastNum=new ArrayList<Integer>();
		List<Integer> tempNum=new ArrayList<Integer>();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int firstR = ca.getFirstRow();
			int lastR=ca.getLastRow();
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (firstC == columIndex) {
					mergerdNum.add(firstR);
					lastNum.add(lastR);
			}
		}
		//如果不是合并单元格，也需要把行数放入到list中
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			boolean ismer=false;
			for(int j=0;j<mergerdNum.size();j++){
				if(i>=mergerdNum.get(j)&&i<=lastNum.get(j)){
					ismer=true;
					continue;
				}
			}
			if(!ismer){
				tempNum.add(i);
			}
		}
		mergerdNum.addAll(tempNum);
		sort(mergerdNum);
		return mergerdNum;
	}

	public static void sort(List<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 1; j < list.size() - i; j++) {
				Integer a;
				if ((list.get(j - 1)).compareTo(list.get(j)) > 0) {
					a = list.get(j - 1);
					list.set((j - 1), list.get(j));
					list.set(j, a);
				}
			}
		}
	}

	public static boolean isMergedFirst(List<Integer> nums, Integer rowIndex) {
		for (Integer i : nums) {
			if (i.equals(rowIndex)) {
				return true;
			}
		}
		return false;

	}

	public static XSSFCell getMergedCell(Integer colIndex, Integer rowIndex,
			List<Integer> mergerdNum, XSSFSheet sheet) {
		List<Integer> tempList = new ArrayList<Integer>(mergerdNum);
		Integer tempI = 0;
		for (int i = 0; i < tempList.size(); i++) {
			if (rowIndex < tempList.get(i)) {
				tempI = i - 1;
				return sheet.getRow(tempList.get(i - 1)).getCell(colIndex);
			}
		}
		
		if(tempI==0){
			tempI=tempList.get(tempList.size()-1);
		}
		return sheet.getRow(tempI).getCell(colIndex);
	}

	/**
	 * 判断是不是合并单元格的第一行
	 * 
	 * @param sheet
	 * @param cell
	 * @return
	 */
	public static boolean isMergedRegionFirstCell(Sheet sheet, XSSFCell cell) {
		// 得到一个sheet中有多少个合并单元格
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn(); // 起始列
			int lastC = ca.getLastColumn();// 结束列
			int firstR = ca.getFirstRow();// 起始行
			int lastR = ca.getLastRow();// 结束行
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (cell.getColumnIndex() <= lastC
					&& cell.getColumnIndex() >= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					if (cell.getRowIndex() == firstR
							&& cell.getColumnIndex() == firstC)
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */

	public static boolean isMergedRegion(XSSFSheet sheet, int row, int column) {
		// 得到一个sheet中有多少个合并单元格
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress range = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据行号返回合并的单元格第一格，以及合并单元格的4个参数,方便后续添加边框
	 * 
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 * @return
	 */
	public static Map<String, Object> getMergedRegionValueMap(XSSFSheet sheet,
			int rowIndex, int columnIndex) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isMerge", false);
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			org.apache.poi.ss.util.CellRangeAddress ca = sheet
					.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (rowIndex >= firstRow && rowIndex <= lastRow) {
				if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
					XSSFRow fRow = sheet.getRow(firstRow);
					XSSFCell fCell = fRow.getCell(firstColumn);
					resultMap.put("cell", fCell);
					resultMap.put("mergeCount", i);
					resultMap.put("isMerge", true);
					break;
				}
			}
		}
		return resultMap;
	}

	public static void addBorderAndComment(XSSFWorkbook wb,
			List<Map<String, Object>> errorList) {
		removeBorderAndComment(wb);
		for (Map<String, Object> error : errorList) {
			addBorderAndComment(wb, error);
		}
	}

	/**
	 * 添加边框及注释
	 * 
	 * @param wb
	 * @param error
	 */
	public static void addBorderAndComment(XSSFWorkbook wb,
			Map<String, Object> error) {
		XSSFSheet sheet = wb.getSheet((String) error.get("sheetName"));
		Integer commentRow = (Integer) error.get("rowIndex") - 1;
		Integer commentCol = (Integer) error.get("colIndex");
		String errorMsg = (String) error.get("errorMsg");
		addComment(sheet, commentRow, commentCol, errorMsg);
		if (error.get("isMerge") != null && (Boolean) error.get("isMerge")) {
			Integer mergeCount = (Integer) error.get("mergeCount");
			addMergeCellColorBorder(wb, sheet, mergeCount);
		} else {
			addSingleCellColorBorder(wb, sheet, commentRow, commentCol);
		}
	}

	/**
	 * 去掉WB里所有SHEET的边框和注释
	 * 
	 * @param wb
	 */
	public static void removeBorderAndComment(XSSFWorkbook wb) {
		XSSFCellStyle style = getStyle(wb, BORDER_THIN, XSSFCOLOR_BLACK);
		for (Iterator<XSSFSheet> sheetIter = wb.iterator(); sheetIter.hasNext();) {
			XSSFSheet sheet = sheetIter.next();
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				if (null != row) {
					for (int j = 0; j < row.getLastCellNum(); j++) {
						XSSFCell cell = row.getCell(j);
						if (null != cell) {
							cell.removeCellComment();
							cell.setCellStyle(style);
						}
					}
				}
			}
		}
	}

	/**
	 * 添加备注
	 * 
	 * @param sheet
	 * @param row
	 * @param col
	 * @param errorMsg
	 */
	public static void addComment(XSSFSheet sheet, Integer row, Integer col,
			String errorMsg) {
		XSSFRow xssfRow = sheet.getRow(row);
		if (null == xssfRow) {
			xssfRow = sheet.createRow(row);
		}
		XSSFCell cell = xssfRow.getCell(col);
		if (null == cell) {
			cell = xssfRow.createCell(col);
		}
		XSSFComment comment = cell.getCellComment();
		XSSFDrawing xd = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 3,3, (short) 5, 6);
		if (comment == null) {
			comment = xd.createCellComment(anchor);
			comment.setString(new XSSFRichTextString(errorMsg));
		} else {
			XSSFRichTextString string = comment.getString();
			// 有多条就换行
			comment.setString(string + "\n" + errorMsg);
		}
		cell.setCellComment(comment);
	}
	
	public static void addComment(XSSFCell cell,XSSFSheet sheet, Integer row, Integer col,
			String errorMsg) {
		if(cell==null){
			XSSFRow xssfRow = sheet.getRow(row);
			if (null == xssfRow) {
				xssfRow = sheet.createRow(row);
			}
			cell = xssfRow.getCell(col);
			if (null == cell) {
				cell = xssfRow.createCell(col);
			}
		}
		XSSFComment comment = cell.getCellComment();
		XSSFDrawing xd = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 3,
				3, (short) 5, 6);
		if (comment == null) {
			comment = xd.createCellComment(anchor);
			comment.setString(new XSSFRichTextString(errorMsg));
		} else {
			XSSFRichTextString string = comment.getString();
			// 有多条就换行
			comment.setString(string + "\n" + errorMsg);
		}
		cell.setCellComment(comment);	
	}
	/**
	 * 单格添加边框
	 * 
	 * @param wb
	 * @param sheet
	 * @param row
	 * @param col
	 */
	public static void addSingleCellColorBorder(XSSFWorkbook wb,
			XSSFSheet sheet, Integer row, Integer col) {
		XSSFRow xssfRow = sheet.getRow(row);
		XSSFCell cell = xssfRow.getCell(col);
		
		if (null != cell) {
			XSSFCellStyle style=getStyle(wb, BORDER_MEDIUM, XSSFCOLOR_RED);
			cell.setCellStyle(style);
			
		}
		
	}
	/**
	 * 新建样式
	 * 
	 * @param wb
	 * @param border
	 * @param xssfColor
	 * @return
	 */
	public static XSSFCellStyle getStyle(XSSFWorkbook wb, short border,
			XSSFColor xssfColor) {
			XSSFCellStyle style;
		//先判断map的长度，如果大于100，先清空
			if(styles.size()>100){
				styles.clear();
			}
	
			if(styles.containsKey(wb)){
				style=styles.get(wb);
				if(border!=style.getBorderBottom()||xssfColor!=style.getBottomBorderXSSFColor()){
					style=wb.createCellStyle();
					style.setBorderBottom(border);
					style.setBorderLeft(border);
					style.setBorderRight(border);
					style.setBorderTop(border);
					style.setBottomBorderColor(xssfColor);
					style.setTopBorderColor(xssfColor);
					style.setLeftBorderColor(xssfColor);
					style.setRightBorderColor(xssfColor);
					styles.put(wb,style);
				}
			}else{
				style=wb.createCellStyle();
				style.setBorderBottom(border);
				style.setBorderLeft(border);
				style.setBorderRight(border);
				style.setBorderTop(border);
				style.setBottomBorderColor(xssfColor);
				style.setTopBorderColor(xssfColor);
				style.setLeftBorderColor(xssfColor);
				style.setRightBorderColor(xssfColor);
				styles.put(wb, style);
			}
			return style;
	}

	public static void addMergeCellColorBorder(XSSFWorkbook wb,
			XSSFSheet sheet, Integer mergeCount) {
		CellRangeAddress cs = sheet.getMergedRegion(mergeCount);
		RegionUtil.setBorderBottom(color, cs, sheet, wb);
		RegionUtil.setBottomBorderColor(color, cs, sheet, wb);
		RegionUtil.setBorderRight(color, cs, sheet, wb);
		RegionUtil.setRightBorderColor(color, cs, sheet, wb);
	}

	public static int getWordCount(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 3;

		}
		return length;

	}

	/**
	 * 根据行指定移除样式
	 * 
	 * @param wb
	 * @param row
	 */
	public static void removeComment(XSSFWorkbook wb, Integer row) {
		XSSFCellStyle style = getStyle(wb, BORDER_THIN, XSSFCOLOR_BLACK);
		for (Iterator<XSSFSheet> sheetIter = wb.iterator(); sheetIter.hasNext();) {
			XSSFSheet sheet = sheetIter.next();
			if (row > 0) {
				for (int i = row; i <= sheet.getLastRowNum(); i++) {
					XSSFRow r = sheet.getRow(i);
					if (null != r) {
						for (int j = 0; j < r.getLastCellNum(); j++) {
							XSSFCell cell = r.getCell(j);
							if (null != cell) {
								cell.removeCellComment();
								cell.setCellStyle(style);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 合并单元格
	 * 
	 * @param firstRow
	 *            起始行号
	 * @param firstCol
	 *            终止行号
	 * @param lastRow
	 *            起始列号
	 * @param lastCol
	 *            终止列号
	 */
	public static CellRangeAddress getCellRangeAddress(int firstRow,
			int lastRow, int firstCol, int lastCol) {
		return new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
	}
	/**
	 * 获得单元格的值（包括合并的单元格）
	 * @param indexI
	 * @param row
	 * @return
	 */
	public static String getCellStringValues(XSSFSheet sheet, int rowNumber, int  columnNumber) {
		String tempString  = "";
		XSSFRow row = sheet.getRow(rowNumber);
		boolean mergedFlag = isMergedRegion(sheet, rowNumber, columnNumber);
		if(mergedFlag){
			tempString = getMergedRegionValue(columnNumber, rowNumber, sheet);
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
	public static String getCellStringValue(int indexI, XSSFRow row) {
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
     public static String getMergedRegionValue(int column, int row , XSSFSheet sheet){    
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
 	 * 错误信息map
 	 * @param sheetName
 	 * @param row
 	 * @param col
 	 * @param msg
 	 * @return
 	 */
 	public static JSONObject addErrorInfo(String sheetName, int row, int col,String msg) {
 		JSONObject errorJson = new JSONObject();
 		errorJson.put("sheetName", sheetName);
 		errorJson.put("rowIndex", row + 1);
 		errorJson.put("colIndex", col);
 		errorJson.put("errorMsg", msg);
 		return errorJson;
 	}
}
