package com.cwgj.system.sms.api.vo;


import lombok.Data;

@Data
public class CodeVo {

  private String code;

  private String telphone;

  private String key;

  public CodeVo(String code, String telphone, String key) {
    this.code = code;
    this.telphone = telphone;
    this.key = key;
  }

  public CodeVo() {}
}
