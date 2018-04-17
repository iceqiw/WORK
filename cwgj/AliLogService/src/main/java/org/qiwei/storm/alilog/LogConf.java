package org.qiwei.storm.alilog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LogConf {

  private final static Properties properties = new Properties();

  static {
    try (InputStream in = LogConf.class.getClassLoader().getResourceAsStream("base.properties")) {
      properties.load(in);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static final String conumser_group_name = "logservice";   // 每个Topology 需要设定唯一的 consumer group 名字，不能为空，支持 [a-z][0-9] 和 '_'，'-'，长度在 [3-63] 字符，只能以小写字母和数字开头结尾
  public static final String project = "event-logs-hup-prod";    // 日志服务的Project
  public static final String logstore = "event-log-store";   // 日志服务的Logstore

  public static final String access_id = properties.getProperty("access_id");
  public static final String access_key = properties.getProperty("access_key");

  public static final String mode = properties.getProperty("model");  // 使用本地测试模式
  public static final String hbase_url = properties.getProperty("hbase_url");
  public static final String endpoint = properties.getProperty("endpoint");   // 日志服务访问域名

}
