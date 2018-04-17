package com.cwgj.system.sms.alisms.handler;


public class SmsResult {


  private String msg;

  private String code;

  private boolean success;


  public SmsResult(String msg, String code) {
    this.msg = msg;
    this.code = code;
    this.success = false;
  }

  public SmsResult() {
    this.msg = "OK";
    this.code = "0";
    this.success = true;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
