package com.cwgj.bigdata.api.data_provider.model;


import lombok.Data;

@Data
public class AdvDetail {
    private String pushId;
    private String pushType;
    private String advId;
    private String advName;
    private String money;
    private String telephone;
    private String isRead;
    private String pushTime;
    private String readTime;
    private String ds;
}
