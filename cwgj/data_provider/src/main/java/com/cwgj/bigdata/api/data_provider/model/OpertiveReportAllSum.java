package com.cwgj.bigdata.api.data_provider.model;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class OpertiveReportAllSum {

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
  private Long totalonlinemoney;
  private Long totalofflinemoney;
}
