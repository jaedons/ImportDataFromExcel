package com.fjhw.main;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.fjhw.entity.XZJJ;
import com.fjhw.util.AllFilePathFromLocal;

public class Main {
	public static void main(String[] args) {
		String[] downtowns = { "抚州", "赣州", "吉安", "景德镇", "九江", "南昌", "萍乡", "上饶",
				"新余", "宜春", "鹰潭" };
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入"+downtowns[10]+"路径");
		String path = scanner.nextLine();
		// 遍历获取市下的县级
		List<XZJJ> list = DataFromExcel.GetDataFromExcel(path);
		// 去除一些缺字段的数据,并记录
		DataToDataBase.addList(list);
	}
	@Test
	public void getAllPath(){
		String[] downtowns = { "抚州", "赣州", "吉安", "景德镇", "九江", "南昌", "萍乡", "上饶",
				"新余", "宜春", "鹰潭" };
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入"+downtowns[5]+"路径");
		String filepath = scanner.nextLine();
		List<String> pathes = AllFilePathFromLocal.getAllFiles(filepath);
		for(String path:pathes){
			System.out.println(path);
		}
	}
	
}
