package com.cwgj.bigdata.api.data_provider.model;


import lombok.Data;

@Data
public class NormBussData {
    private String eventName;
    private String cityName;
    private String bussName;
    private String ds;
    private Long tv;
}
