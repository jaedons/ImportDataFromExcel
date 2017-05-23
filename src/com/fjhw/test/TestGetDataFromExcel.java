package com.fjhw.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fjhw.biz.DataFromExcelXLS;
import com.fjhw.biz.DataFromExcelXLSX;
import com.fjhw.entity.ClassifiedExcel;
import com.fjhw.entity.XZJJ;
import com.fjhw.main.DataFromExcel;
import com.fjhw.util.AllFilePathFromLocal;
import com.fjhw.util.ClassifyExcel;

public class TestGetDataFromExcel {
	public static String commonPath = "E:\\����\\��������\\20170516\\201612_������������B\\201612_������������\\�˴�\\";
	@Test
	public void getDataFromXLS(){
//		String filepath = "�¸�������������Ϣ����վ����ƽ̨���ݵ����.xls";
		String filepath = commonPath+"������.xls";
		List<List<String>> datas = DataFromExcelXLS.getDataFromXLS(filepath,"����������");
		for(List<String> data:datas){
			String str ="";
			for(String d:data){
				str +=" "+d;
			}
			System.out.println(str);
			str = "";
		}
	}
	@Test
	public void getDataFromXLSX(){
		String filepath = commonPath+"������.xlsx";
		List<List<String>> datas = DataFromExcelXLSX.getDataFromXLSX(filepath, "����������");
		for(List<String> data:datas){
			String str ="";
			for(String d:data){
				str +=" "+d;
			}
			System.out.println(str);
			str = "";
		}
	}
	@Test
	public void getAllData(){
		// ��ȡָ��Ŀ¼�µ����з�Ŀ¼�ĵ�(Excel�ĵ�)
				List<String> list = AllFilePathFromLocal.getAllFiles("E:"+File.separator+"����"+File.separator+"��������"+File.separator+"20170516"+File.separator+"201612_������������B"+File.separator+"201612_������������"+File.separator+"����");
				// ���ĵ����з��� xls��xlsx
				ClassifiedExcel classifiedExcel = ClassifyExcel.classifyExcel(list);
				List<XZJJ> xzjjs = new ArrayList<XZJJ>();
				// ��ȡxls��ʽ�ĵ��е�����
				xzjjs = DataFromExcel.getDataFromXLSs(xzjjs, classifiedExcel.getXlsList(), "xls");
				// ��ȡxlsx��ʽ�ĵ��е�����
				xzjjs = DataFromExcel.getDataFromXLSs(xzjjs, classifiedExcel.getXlsxList(), "xlsx");
				for(XZJJ xzjj:xzjjs){
					System.out.println(xzjj);
				}
				
	}
}
