package com.cwgj.bigdata.api.data_collecter.controller;


import com.alibaba.fastjson.JSON;
import com.cwgj.bigdata.api.data_collecter.model.ApiResponse;
import com.cwgj.bigdata.api.data_collecter.vo.EventVO;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API")
public class V1ApiController {

  private final static Logger logger = LoggerFactory.getLogger("V1ApiController");

  @ApiOperation(value = "事件采集", notes = "事件采集api")
  @PostMapping("/v1")
  public ApiResponse getAdvDetail(@Valid @RequestBody EventVO vo) {
    logger.info(JSON.toJSONString(vo));
    return new ApiResponse("ok", 200);
  }

}
