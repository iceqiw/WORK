package com.cwgj.bigdata.api.data_provider.model;


import lombok.Data;

@Data
public class WechatPush {

  private String cityName;
  private String parkName;
  private String ds;
  private Long wechatIn;
  private Long wechatOut;
  private Long advancePay;
  private Long paySuccess;

}
