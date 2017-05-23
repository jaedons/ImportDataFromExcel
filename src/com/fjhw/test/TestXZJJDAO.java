package com.fjhw.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.fjhw.dao.XZJJDAO;
import com.fjhw.entity.XZJJ;
import com.fjhw.main.DataFromExcel;

public class TestXZJJDAO {
	XZJJDAO xzjjdao = new XZJJDAO();
	@Test
	public void test1(){
		List<XZJJ> list = DataFromExcel.GetDataFromExcel("E:"+File.separator+"工作"+File.separator+"福建华网"+File.separator+"20170516"+File.separator+"201612_各县乡镇数据B"+File.separator+"201612_各县乡镇数据"+File.separator+"抚州");
		xzjjdao.addList(list);
	}
}
