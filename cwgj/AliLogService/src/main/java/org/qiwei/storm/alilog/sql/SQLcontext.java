package org.qiwei.storm.alilog.sql;

public class SQLcontext {

  public static final String UPSET_SAVE_ADV = "UPSERT INTO ADV_PUSH(PUSH_ID,PUSH_TYPE,ADV_ID,ADV_NAME,MONEY,TELEPHONE,IS_READ,PUSH_TIME) VALUES('%s','%s','%s','%s',%s,'%s','0','%s')";
  public static final String UPSET_UPDATA_ADV = "UPSERT INTO ADV_PUSH(PUSH_ID,PUSH_TYPE,ADV_ID,IS_READ,READ_TIME) VALUES('%s','%s','%s','1','%s')";
  public static final String UPSET_STAT_DATA_USER = "UPSERT INTO STAT_DATA(STATKEY,STATVALUE) VALUES('%s',NEXT VALUE FOR STAT.USERS)";
  public static final String UPSET_STAT_DATA_ORDER = "UPSERT INTO STAT_DATA(STATKEY,STATVALUE) VALUES('%s',NEXT VALUE FOR STAT.ORDERS)";
  public static final String UPSET_STAT_DAY_DATA = "UPSERT INTO STAT_DAY_DATA(STATKEY,STATDATE,STATVALUE) VALUES('%s','%s','%s')";
}
