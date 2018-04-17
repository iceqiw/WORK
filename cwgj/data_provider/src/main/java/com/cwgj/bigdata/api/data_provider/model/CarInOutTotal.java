package com.cwgj.bigdata.api.data_provider.model;


import lombok.Data;

@Data
public class CarInOutTotal {

  private String cityName;
  private String parkName;
  private String ds;
  private Long carMonthIn;
  private Long carTempIn;
  private Long tempCar;

}
