package com.fjhw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
  /** �ر����ݿ� */
  public static void closeConnection(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  /** �ر����ݿ� */
  public static void closeStatement(Statement statement) {
    try {
      if (statement != null) {
        statement.close();
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * �������ݿ�
   * 
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static Connection getConnection() throws SQLException,
      ClassNotFoundException {
	  
    Class.forName(PropertiesUtil.getValue("driverClass"));
    String url = PropertiesUtil.getValue("jdbcUrl");
    String user = PropertiesUtil.getValue("user");
    String password = PropertiesUtil.getValue("password");
    //orclΪ���ݿ��SID
    Connection conn= DriverManager.getConnection(url,user,password);
    return conn;
  }
}
