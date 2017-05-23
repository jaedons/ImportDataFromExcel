package com.fjhw.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.fjhw.util.DBUtil;
import com.fjhw.util.StringUtil;

public class DataDAO {
	/**���涨��˳�򹹽������Ե�sql*/
	public String addSQL(List<String> values,String tableName){
		StringBuffer addSQL = new StringBuffer("insert into "+tableName);
		// �ֶ�ֵ���ֵ�SQL
		StringBuffer valuesSQL = new StringBuffer(" values( "+tableName+"_seq.nextval,");
		for(String value:values){
			valuesSQL.append("'"+StringUtil.trim(value)+"'").append(", ");
		}
		valuesSQL.deleteCharAt(valuesSQL.lastIndexOf(",")).append(" ) ");
		return addSQL.append(valuesSQL).toString();
	}
	/**��������*/
	void executeBatch(List<String> sqls){
		// ִ�в������
				Connection conn = null;
				Statement statement = null;
				try {
					conn = DBUtil.getConnection();
					statement = conn.createStatement();
					for(String sql:sqls){
						statement.addBatch(sql);
					}
					// TODO 
					statement.executeBatch();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					DBUtil.closeStatement(statement);
					DBUtil.closeConnection(conn);
				}
	}
	/**ִ�е���sql*/
	void executeOne(String sql){
		// ִ�в������
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBUtil.getConnection();
			statement = conn.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeStatement(statement);
			DBUtil.closeConnection(conn);
		}
	}
}
