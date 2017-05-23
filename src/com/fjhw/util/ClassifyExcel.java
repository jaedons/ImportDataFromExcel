package com.fjhw.util;

import java.util.ArrayList;
import java.util.List;

import com.fjhw.entity.ClassifiedExcel;

/**½«excel·ÖÀà*/
public class ClassifyExcel {
	public static ClassifiedExcel classifyExcel(List<String> list){
		List<String> xlsList = new ArrayList<String>();
		List<String> xlsxList = new ArrayList<String>();
		for(String filepath:list){
			if(filepath.matches(".*\\.xls")){
				xlsList.add(filepath);
			}else{
				xlsxList.add(filepath);
			}
		}
		ClassifiedExcel classifiedExcel = new ClassifiedExcel();
		classifiedExcel.setXlsList(xlsList);
		classifiedExcel.setXlsxList(xlsxList);
		return classifiedExcel;
	}
}
