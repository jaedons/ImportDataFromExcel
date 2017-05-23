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

/** ��ȡ��xlsx��β��excel�ļ� */
public class DataFromExcelXLSX {
	public static List<List<String>> getDataFromXLSX(String filePath,
			String sheetName) {
		List<List<String>> datas = new ArrayList<List<String>>();
		try {
			// // TODO �������
			// if (!filePath.matches(".*\\.xlsx")) {
			// System.out.println(filePath);
			// return null;
			// }
			InputStream is = new FileInputStream(filePath);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// ��ȡÿһ��������
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// ֻ��������鲿��
				String sheetName2 = xssfSheet.getSheetName();
				// ��sheetNameΪnull ��ʾ��������sheet ; ����Ϊnull,�����ָ��sheet
				if (sheetName != null && !sheetName2.trim().contains(sheetName))
					continue;
				// ��ȡ��ǰ��������ÿһ��
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					List<String> data = new ArrayList<String>();
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null)
						continue;
					int cellNum = xssfRow.getLastCellNum();
					if (xssfRow != null) {
						for (int i = 0; i < cellNum; i++) {
							XSSFCell cell = xssfRow.getCell(i);
							// cellΪnull, ���ʾ�˵�Ԫ��δ��������,����Ȼ��������ռλ
							if (cell == null) {
								data.add("@");
								continue;
							}
							// ȥ���ո��е��ַ�
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
			// ת�����ݸ�ʽ
		} catch (Exception e) {
			System.out.println("�����Ŀ¼·��" + filePath);
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
