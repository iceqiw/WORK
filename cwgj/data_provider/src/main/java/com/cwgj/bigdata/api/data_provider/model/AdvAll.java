package com.cwgj.bigdata.api.data_provider.model;

import lombok.Data;

@Data
public class AdvAll {

  private String event;
  private String pushType;
  private String advId;
  private Long sendCount;
  private Long costMoney;
  private String ds;
}
