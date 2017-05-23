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

/**��ȡ��xls��β��excel�ļ�*/
public class DataFromExcelXLS {
	public static List<List<String>> getDataFromXLS(String filePath,String sheetName){
		List<List<String>> datas = new ArrayList<List<String>>();
		try {
			InputStream is = new FileInputStream(filePath);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// ��ȡÿһ��������
			for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// ֻ��������鲿��
				String sheetName2 = hssfSheet.getSheetName();
				// ��sheetNameΪnull ��ʾ��������sheet ; ����Ϊnull,�����ָ��sheet
				if(sheetName!=null&&!sheetName2.trim().contains(sheetName)) continue;
				// ��ȡ��ǰ��������ÿһ��
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					List<String> data = new ArrayList<String>();
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					// Ϊ��ʱ ��¼Ϊ����Ϣ 
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
		} catch (Exception e) {
			System.out.println("�����Ŀ¼·��" + filePath);
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
