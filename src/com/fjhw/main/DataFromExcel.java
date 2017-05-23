package com.fjhw.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fjhw.biz.DataFromExcelXLS;
import com.fjhw.biz.DataFromExcelXLSX;
import com.fjhw.entity.ClassifiedExcel;
import com.fjhw.entity.XZJJ;
import com.fjhw.util.AllFilePathFromLocal;
import com.fjhw.util.ClassifyExcel;
import com.fjhw.util.StringUtil;

public class DataFromExcel {
	public static List<XZJJ> GetDataFromExcel(String path) {
		// 获取指定目录下的所有非目录文档(Excel文档)
		List<String> list = AllFilePathFromLocal.getAllFiles(path);
		// 对文档进行分类 xls和xlsx
		ClassifiedExcel classifiedExcel = ClassifyExcel.classifyExcel(list);
		List<XZJJ> xzjjs = new ArrayList<XZJJ>();
		// 获取xls格式文档中的数据
		xzjjs = getDataFromXLSs(xzjjs, classifiedExcel.getXlsList(), "xls");
		// 获取xlsx格式文档中的数据
		xzjjs = getDataFromXLSs(xzjjs, classifiedExcel.getXlsxList(), "xlsx");
		return xzjjs;
	}

	public static List<XZJJ> getDataFromXLSs(List<XZJJ> xzjjs,
			List<String> xlsList, String pattern) {
		// 用以去除集合中为null或者为""的数据
		List<String> nullList = new ArrayList<String>();
		nullList.add(null);
		nullList.add("");
		// 获取 xls类型中 指定sheet的数据
		for (String filepath : xlsList) {
			List<List<String>> datas = "xls".equals(pattern.trim()) ? DataFromExcelXLS
					.getDataFromXLS(filepath, "乡镇基本情况") : DataFromExcelXLSX
					.getDataFromXLSX(filepath, "乡镇基本情况");
			// 将数据封装到xzjj中
			for (List<String> data : datas) {
				if(data==null||data.size()==0) continue;		
				// 去除null及"" 若只剩占位符@ 则跳过此次数据封装
				data.removeAll(nullList);
				if(data.size()==1&&"@".equals(data.get(0))){
					continue;
				}
				XZJJ xzjj = convert2Entity(data);
				if (xzjj != null) {
					xzjj.setPath(filepath);
					xzjjs.add(xzjj);
				}
			}
		}
		return xzjjs;
	}

	public static XZJJ convert2Entity(List<String> data) {
		if (data == null || data.size() == 0) {
			return null;
		}
		XZJJ xzjj = new XZJJ();
		int size = data.size();
		for (int i = 0; i < size; i++) {
			String value = data.get(i);
			if (i == 0 && value != null) {
				xzjj.setOrder(value);
			} else if (i == 1) {
				xzjj.setDowntown(value);
			} else if (i == 2) {
				xzjj.setCounty(value);
			} else if (i == 3) {
				xzjj.setTownshipName(value);
			} else if (i == 4) {
				xzjj.setTownshipProfiles(value);
			} else if (i == 5) {
				xzjj.setAgricultureState(value);
			} else {
				// 获取的值为null
				continue;
			}
		}
		return xzjj;
	}
}
