package com.fjhw.biz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fjhw.util.StringUtil;

/** 读取以xlsx结尾的excel文件 */
public class DataFromExcelXLSX {
	public static List<List<String>> getDataFromXLSX(String filePath,
			String sheetName) {
		List<List<String>> datas = new ArrayList<List<String>>();
		try {
			// // TODO 输出处理
			// if (!filePath.matches(".*\\.xlsx")) {
			// System.out.println(filePath);
			// return null;
			// }
			InputStream is = new FileInputStream(filePath);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// 获取每一个工作薄
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// 只添加乡镇简介部分
				String sheetName2 = xssfSheet.getSheetName();
				// 若sheetName为null 表示访问所有sheet ; 若不为null,则访问指定sheet
				if (sheetName != null && !sheetName2.trim().contains(sheetName))
					continue;
				// 获取当前工作薄的每一行
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					List<String> data = new ArrayList<String>();
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null)
						continue;
					int cellNum = xssfRow.getLastCellNum();
					if (xssfRow != null) {
						for (int i = 0; i < cellNum; i++) {
							XSSFCell cell = xssfRow.getCell(i);
							// cell为null, 则表示此单元格未填入数据,但依然保存用以占位
							if (cell == null) {
								data.add("@");
								continue;
							}
							// 去掉空格换行等字符
							String value = getValue(cell);
							value = StringUtil.removeEnter(value);
							value = StringUtil.removeBlank(value);
							if(value.equals(""))value="@";
							data.add(value);
						}
					}
					datas.add(data);
				}
			}
			// 转换数据格式
		} catch (Exception e) {
			System.out.println("出错的目录路径" + filePath);
			e.printStackTrace();
		}
		return datas;
	}

	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

}
