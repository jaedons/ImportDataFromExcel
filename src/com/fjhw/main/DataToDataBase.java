package com.fjhw.main;

import java.util.List;

import com.fjhw.dao.XZJJDAO;
import com.fjhw.entity.XZJJ;

public class DataToDataBase {
	public static void addList(List<XZJJ> list){
		XZJJDAO xzjjdao = new XZJJDAO();
		xzjjdao.addList(list);
	}
}
