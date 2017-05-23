package com.fjhw.dao;

import java.util.ArrayList;
import java.util.List;


import com.fjhw.entity.XZJJ;
import com.fjhw.util.StringUtil;

public class XZJJDAO extends DataDAO{
	public void addList(List<XZJJ> list){
		// ��ȡ���в������
		List<String> addSQLs = new ArrayList<String>();
		for(XZJJ xzjj:list){
			List<String> values = new ArrayList<String>();
			// ����� ������ ���� ������ ������˳�����
			values.add(xzjj.getDowntown());
			values.add(xzjj.getCounty());
			values.add(xzjj.getTownshipName());
			values.add(xzjj.getTownshipProfiles());
			
			// ������
			String profiles =  xzjj.getTownshipProfiles();
			if(profiles==null||profiles.length()>1100){
				System.out.println("���������ݽϴ�"+values);
				continue;
			}
//			// ������  TODO
//			String townshipName = xzjj.getTownshipName();
//			if(townshipName==null||townshipName.length()>125){
//				System.out.println("�������ϴ�:"+values);
//				continue;
//			}
			String addSQL = super.addSQL(values, "pm_xzjj");
			addSQL  = StringUtil.removeEnter(addSQL);
			// ��addSQL�к���@ռλ�� �����ӡ���Ҳ���������ִ��
			if(addSQL.contains("@")){
				System.out.println(xzjj);
				continue;
			}
			addSQLs.add(addSQL);
		}
		super.executeBatch(addSQLs);
	}
}
