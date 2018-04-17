package com.cwgj.bigdata.api.data_provider.model;

import lombok.Data;

@Data
public class AdvTotalByDs {
  private Long pushCount;
  private Long readCount;
  private Long pushCost;
  private String ds;
}
