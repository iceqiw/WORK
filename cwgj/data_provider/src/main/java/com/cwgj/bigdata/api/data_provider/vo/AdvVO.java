package com.cwgj.bigdata.api.data_provider.vo;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class AdvVO extends BaseVO {

  private final static Logger logger = LoggerFactory.getLogger("AdvVO");

  private String advId;
  private String event;

  private Long start;
  private int size;

}
