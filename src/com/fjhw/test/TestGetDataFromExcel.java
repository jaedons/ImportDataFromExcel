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
	public static String commonPath = "E:\\工作\\福建华网\\20170516\\201612_各县乡镇数据B\\201612_各县乡镇数据\\宜春\\";
	@Test
	public void getDataFromXLS(){
//		String filepath = "新干县乡镇气象信息服务站工作平台数据调查表.xls";
		String filepath = commonPath+"奉新县.xls";
		List<List<String>> datas = DataFromExcelXLS.getDataFromXLS(filepath,"乡镇基本情况");
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
		String filepath = commonPath+"奉新县.xlsx";
		List<List<String>> datas = DataFromExcelXLSX.getDataFromXLSX(filepath, "乡镇基本情况");
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
		// 获取指定目录下的所有非目录文档(Excel文档)
				List<String> list = AllFilePathFromLocal.getAllFiles("E:"+File.separator+"工作"+File.separator+"福建华网"+File.separator+"20170516"+File.separator+"201612_各县乡镇数据B"+File.separator+"201612_各县乡镇数据"+File.separator+"抚州");
				// 对文档进行分类 xls和xlsx
				ClassifiedExcel classifiedExcel = ClassifyExcel.classifyExcel(list);
				List<XZJJ> xzjjs = new ArrayList<XZJJ>();
				// 获取xls格式文档中的数据
				xzjjs = DataFromExcel.getDataFromXLSs(xzjjs, classifiedExcel.getXlsList(), "xls");
				// 获取xlsx格式文档中的数据
				xzjjs = DataFromExcel.getDataFromXLSs(xzjjs, classifiedExcel.getXlsxList(), "xlsx");
				for(XZJJ xzjj:xzjjs){
					System.out.println(xzjj);
				}
				
	}
}
