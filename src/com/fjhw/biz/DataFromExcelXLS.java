package com.fjhw.biz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fjhw.util.StringUtil;

/**读取以xls结尾的excel文件*/
public class DataFromExcelXLS {
	public static List<List<String>> getDataFromXLS(String filePath,String sheetName){
		List<List<String>> datas = new ArrayList<List<String>>();
		try {
			InputStream is = new FileInputStream(filePath);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获取每一个工作薄
			for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// 只添加乡镇简介部分
				String sheetName2 = hssfSheet.getSheetName();
				// 若sheetName为null 表示访问所有sheet ; 若不为null,则访问指定sheet
				if(sheetName!=null&&!sheetName2.trim().contains(sheetName)) continue;
				// 获取当前工作薄的每一行
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					List<String> data = new ArrayList<String>();
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					// 为空时 记录为空信息 
//					String nullStr = hssfSheet.getSheetName()+"=="+rowNum;
//					System.out.println(nullStr);
					if(hssfRow==null) continue;
					int cellNum = hssfRow.getLastCellNum();
					if (hssfRow != null) {
						for(int i=0;i<cellNum;i++){
							HSSFCell cell = hssfRow.getCell(i);
							if(cell==null) {
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
		} catch (Exception e) {
			System.out.println("出错的目录路径" + filePath);
			e.printStackTrace();
				}
		return datas;
	}
	private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            return String.valueOf(hssfCell.getStringCellValue());
        }else{
        	return null;
        }
    }

}
