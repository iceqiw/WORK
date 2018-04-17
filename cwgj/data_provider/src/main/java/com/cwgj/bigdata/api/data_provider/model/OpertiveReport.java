package com.cwgj.bigdata.api.data_provider.model;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class OpertiveReport {

  private String parkid;
  private String parkname;
  private String cityid;
  private String cityname;
  private String parktype;
  private String creattime;
  private Long totalparkspacenum;
  private Long totalordernum;
  private Long totalmonthlynum;
  private Long totalnochargenum;
  private Long totalchargenum;
  private Long totalchargezero;
  private Long totalofflinepayment;
  private Long totalonlinepayment;
  private Long onlinepaymentproportion;
  private Long totalcarnum;
  private Long totaluser;
  private Long newuser;
  private Long newmember;
  private Long newmemberproportion;
  private BigDecimal useractive;
  private BigDecimal totalpush;
  private String actdate;
  private Long totalonlinemoney;
  private Long totalofflinemoney;
}
