package com.cwgj.system.sms.common.wrapper;

public interface SmsWrapper {


  public final static String DEFAULTSMS = "aliSmsHandler";


  boolean login(String code, String phone);


  boolean register(String code, String phone);

  String getName();



}
