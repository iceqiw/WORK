package com.cwgj.bigdata.api.data_collecter.model;

import lombok.Data;

@Data
public class ApiResponse {

  private int retCode = 200;
  private String message = "ok";
  private Long timestamp = System.currentTimeMillis();

  public ApiResponse(String message, int code) {
    this.retCode = code;
    this.message = message;
  }

}
