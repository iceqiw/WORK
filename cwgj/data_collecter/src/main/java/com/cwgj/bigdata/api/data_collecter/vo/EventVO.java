package com.cwgj.bigdata.api.data_collecter.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@ApiModel(value = "event")
public class EventVO extends BaseVO {

  private final static Logger logger = LoggerFactory.getLogger("EventVO");


  @NotNull(message = "app名称不能为空")
  @ApiModelProperty(required = true, value = "应用代码 必填")
  private String app;
  @ApiModelProperty(required = true, value = "事件名称 必填")
  @NotNull(message = "事件名称不能为空")
  private String xevent;


  @ApiModelProperty(value = "上报内容,Map(String,String)")
  private Map<String, String> xcontext;

}
