package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.model.AdvAll;
import com.cwgj.bigdata.api.data_provider.model.AdvDetail;
import com.cwgj.bigdata.api.data_provider.model.AdvTotalByDs;
import com.cwgj.bigdata.api.data_provider.service.impl.AdvPushServiceImpl;
import com.cwgj.bigdata.api.data_provider.vo.AdvVO;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adv")
public class AdvController {


  @Autowired
  private AdvPushServiceImpl advPushService;

  @PostMapping("/get_adv_detail")
  @ApiOperation(value = "根据类型获取广告数据，导出")
  public List<AdvDetail> getAdvDetail(@RequestBody AdvVO vo) {
    return advPushService.getAdvDetail(vo);
  }

  @PostMapping("/get_adv_all_advid")
  @ApiOperation(value = "获取广告汇总信息,汇总")
  public List<AdvAll> getAdvAllByAdvId(@RequestBody AdvVO vo) {
    return advPushService.getAdvAllByAdvId(vo);
  }

  @PostMapping("/get_adv_total_pushtype")
  @ApiOperation(value = "获取广告统计信息,按类型")
  public List<AdvAll> getAdvTotalPushType(@RequestBody AdvVO vo) {
    return advPushService.getAdvTotalPushType(vo);
  }

  @PostMapping("/get_adv_list_group_ds")
  @ApiOperation(value = "获取广告统计信息,按日期")
  public List<AdvTotalByDs> getAdvListGroupByDs(@RequestBody AdvVO vo) {
    return advPushService.getAdvListGroupByDs(vo);
  }

}
