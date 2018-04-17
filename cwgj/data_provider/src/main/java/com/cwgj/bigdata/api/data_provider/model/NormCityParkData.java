package com.cwgj.bigdata.api.data_provider.model;


import lombok.Data;

@Data
public class NormCityParkData {
    private String eventName;
    private String cityName;
    private String parkName;
    private String ds;
    private Long tv;
}
