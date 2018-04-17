package com.cwgj.bigdata.api.data_provider.model;


import lombok.Data;

@Data
public class Order {

  private String cityName;
  private String parkName;
  private String ds;
  private Long onlineOrderCount;
  private Long offlineOrderCount;
  private Long onlineOrderAmount;
  private Long offlineOrderAmount;

}
