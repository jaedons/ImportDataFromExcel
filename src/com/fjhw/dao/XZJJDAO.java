package com.fjhw.dao;

import java.util.ArrayList;
import java.util.List;


import com.fjhw.entity.XZJJ;
import com.fjhw.util.StringUtil;

public class XZJJDAO extends DataDAO{
	public void addList(List<XZJJ> list){
		// 获取所有插入语句
		List<String> addSQLs = new ArrayList<String>();
		for(XZJJ xzjj:list){
			List<String> values = new ArrayList<String>();
			// 按序号 设市区 县名 乡镇名 乡镇简介顺序添加
			values.add(xzjj.getDowntown());
			values.add(xzjj.getCounty());
			values.add(xzjj.getTownshipName());
			values.add(xzjj.getTownshipProfiles());
			
			// 乡镇简介
			String profiles =  xzjj.getTownshipProfiles();
			if(profiles==null||profiles.length()>1100){
				System.out.println("乡镇简介数据较大："+values);
				continue;
			}
//			// 乡镇名  TODO
//			String townshipName = xzjj.getTownshipName();
//			if(townshipName==null||townshipName.length()>125){
//				System.out.println("乡镇名较大:"+values);
//				continue;
//			}
			String addSQL = super.addSQL(values, "pm_xzjj");
			addSQL  = StringUtil.removeEnter(addSQL);
			// 若addSQL中含有@占位符 ，则打印并且不进行数据执行
			if(addSQL.contains("@")){
				System.out.println(xzjj);
				continue;
			}
			addSQLs.add(addSQL);
		}
		super.executeBatch(addSQLs);
	}
}
