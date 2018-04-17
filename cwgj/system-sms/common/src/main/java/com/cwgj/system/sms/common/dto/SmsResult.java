package com.cwgj.system.sms.common.dto;


import lombok.Data;

@Data
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

}
