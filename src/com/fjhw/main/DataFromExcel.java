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
		// ��ȡָ��Ŀ¼�µ����з�Ŀ¼�ĵ�(Excel�ĵ�)
		List<String> list = AllFilePathFromLocal.getAllFiles(path);
		// ���ĵ����з��� xls��xlsx
		ClassifiedExcel classifiedExcel = ClassifyExcel.classifyExcel(list);
		List<XZJJ> xzjjs = new ArrayList<XZJJ>();
		// ��ȡxls��ʽ�ĵ��е�����
		xzjjs = getDataFromXLSs(xzjjs, classifiedExcel.getXlsList(), "xls");
		// ��ȡxlsx��ʽ�ĵ��е�����
		xzjjs = getDataFromXLSs(xzjjs, classifiedExcel.getXlsxList(), "xlsx");
		return xzjjs;
	}

	public static List<XZJJ> getDataFromXLSs(List<XZJJ> xzjjs,
			List<String> xlsList, String pattern) {
		// ����ȥ��������Ϊnull����Ϊ""������
		List<String> nullList = new ArrayList<String>();
		nullList.add(null);
		nullList.add("");
		// ��ȡ xls������ ָ��sheet������
		for (String filepath : xlsList) {
			List<List<String>> datas = "xls".equals(pattern.trim()) ? DataFromExcelXLS
					.getDataFromXLS(filepath, "����������") : DataFromExcelXLSX
					.getDataFromXLSX(filepath, "����������");
			// �����ݷ�װ��xzjj��
			for (List<String> data : datas) {
				if(data==null||data.size()==0) continue;		
				// ȥ��null��"" ��ֻʣռλ��@ �������˴����ݷ�װ
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
				// ��ȡ��ֵΪnull
				continue;
			}
		}
		return xzjj;
	}
}
