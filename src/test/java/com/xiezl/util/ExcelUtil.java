package com.xiezl.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	/**读取excel数据
	 * @param path 文件路径
	 * @param sheetNum 需要解析的页数
	 * @param startRow 开始行
	 * @param endRow 结束行
	 * @param startCell 开始列
	 * @param endCell 结束列
	 */
	public static Object [][] read(String path,int sheetNum,int startRow,int endRow,int startCell,int endCell){
		Object [][]datas = new Object [endRow-startRow+1][endCell-startCell+1];
		try {
			//工作簿
			Workbook workbook = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(path));
			//excel的页
			Sheet sheet = workbook.getSheetAt(sheetNum);

			for (int i = startRow; i <= endRow; i++) {
				Row row = sheet.getRow(i-1);
				for (int j = startCell; j <= endCell; j++) {
					Cell cell = row.getCell(j-1,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//设置每一列的数据类型为字符串
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					datas[i-startRow][j-startCell] = value;
//					if (cell==null) {//当取出来的是一个空列，就直接给一个空字符串
//						datas[i-startRow][j-startCell]="";
//					}else{
//						//设置每一列的数据类型为字符串
//						cell.setCellType(CellType.STRING);
//						String value = cell.getStringCellValue();
//						datas[i-startRow][j-startCell] = value;
//					}
				}
			}
			Row row = sheet.getRow(0);
			row.getCell(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas; 
	}
	public static void main(String[] args) {
		Object [][] datas= read("/Excel/ExcelTest.xls",0, 2, 6, 1, 3);
		for (Object[] objects : datas) {
			System.out.println("=============");
			for (Object object : objects) {
				System.out.println(object);
			}
			System.out.println("=============");
		}
	}
}
