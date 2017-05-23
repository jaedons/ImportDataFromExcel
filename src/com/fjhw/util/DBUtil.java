package com.fjhw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
  /** 关闭数据库 */
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
  /** 关闭数据库 */
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
   * 连接数据库
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
    //orcl为数据库的SID
    Connection conn= DriverManager.getConnection(url,user,password);
    return conn;
  }
}
