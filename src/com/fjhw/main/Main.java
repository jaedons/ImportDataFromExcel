package com.fjhw.main;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.fjhw.entity.XZJJ;
import com.fjhw.util.AllFilePathFromLocal;

public class Main {
	public static void main(String[] args) {
		String[] downtowns = { "����", "����", "����", "������", "�Ž�", "�ϲ�", "Ƽ��", "����",
				"����", "�˴�", "ӥ̶" };
		Scanner scanner = new Scanner(System.in);
		System.out.println("����"+downtowns[10]+"·��");
		String path = scanner.nextLine();
		// ������ȡ���µ��ؼ�
		List<XZJJ> list = DataFromExcel.GetDataFromExcel(path);
		// ȥ��һЩȱ�ֶε�����,����¼
		DataToDataBase.addList(list);
	}
	@Test
	public void getAllPath(){
		String[] downtowns = { "����", "����", "����", "������", "�Ž�", "�ϲ�", "Ƽ��", "����",
				"����", "�˴�", "ӥ̶" };
		Scanner scanner = new Scanner(System.in);
		System.out.println("����"+downtowns[5]+"·��");
		String filepath = scanner.nextLine();
		List<String> pathes = AllFilePathFromLocal.getAllFiles(filepath);
		for(String path:pathes){
			System.out.println(path);
		}
	}
	
}
