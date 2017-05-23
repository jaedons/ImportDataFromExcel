package com.fjhw.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.fjhw.util.DBUtil;
import com.fjhw.util.StringUtil;

public class DataDAO {
	/**按规定的顺序构建增加性的sql*/
	public String addSQL(List<String> values,String tableName){
		StringBuffer addSQL = new StringBuffer("insert into "+tableName);
		// 字段值部分的SQL
		StringBuffer valuesSQL = new StringBuffer(" values( "+tableName+"_seq.nextval,");
		for(String value:values){
			valuesSQL.append("'"+StringUtil.trim(value)+"'").append(", ");
		}
		valuesSQL.deleteCharAt(valuesSQL.lastIndexOf(",")).append(" ) ");
		return addSQL.append(valuesSQL).toString();
	}
	/**批量处理*/
	void executeBatch(List<String> sqls){
		// 执行插入语句
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
	/**执行单个sql*/
	void executeOne(String sql){
		// 执行插入语句
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
