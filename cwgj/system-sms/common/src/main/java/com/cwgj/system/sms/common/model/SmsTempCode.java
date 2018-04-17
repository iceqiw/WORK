package com.cwgj.system.sms.common.model;


import lombok.Data;

@Data
public class SmsTempCode {

  private String templeteCode;
  private String description;
  private short status;
}
