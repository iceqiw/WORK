package org.qiwei.storm.alilog.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {

  static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
  static Calendar calendar = new GregorianCalendar();

  public static String getToday() {
    return df.format(System.currentTimeMillis());
  }

  public static String getYestoday() {
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
    return df.format(calendar.getTime());
  }
}
