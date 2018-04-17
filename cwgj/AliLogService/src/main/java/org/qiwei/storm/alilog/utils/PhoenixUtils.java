package org.qiwei.storm.alilog.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.qiwei.storm.alilog.LogConf;

public class PhoenixUtils {

  private static final Logger logger = Logger.getLogger("PhoenixUtils");
  private static Connection connection = null;
  private static Statement statement = null;

  public static void start() {
    try {
      Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
      connection = DriverManager.getConnection(LogConf.hbase_url, "", "");
      statement = connection.createStatement();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void upset(String sql, Object... params) throws SQLException {
    String exeSql = String.format(sql, params);
    logger.debug(exeSql);
    statement.execute(exeSql);
    connection.commit();
  }

  public static void commit() throws SQLException {
    connection.commit();
  }


  public static void stop() {
    try {
      connection.close();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
