package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.mapper.CarInOutTotalMapper;
import com.cwgj.bigdata.api.data_provider.mapper.OrderMapper;
import com.cwgj.bigdata.api.data_provider.mapper.WechatPushMapper;
import com.cwgj.bigdata.api.data_provider.model.CarInOutTotal;
import com.cwgj.bigdata.api.data_provider.model.NormCityParkData;
import com.cwgj.bigdata.api.data_provider.model.Order;
import com.cwgj.bigdata.api.data_provider.model.WechatPush;
import com.cwgj.bigdata.api.data_provider.service.INormService;
import com.cwgj.bigdata.api.data_provider.vo.DetailVO;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/normalcitypark")
public class NormalByCityParkController {


  @Autowired
  @Qualifier("normCityParkDataService")
  private INormService normService;

  @Resource
  private WechatPushMapper wechatPushMapper;

  @Resource
  private OrderMapper orderMapper;

  @Resource
  private CarInOutTotalMapper carInOutTotalMapper;

  @PostMapping("/norms_by_param")
  public Map<String, List<NormCityParkData>> getNormsByDs(@Valid @RequestBody NormDataVO vo) {
    return normService.getNormsByParam(vo);
  }

  @PostMapping("/norms_sum_by_param")
  public Map<String, List<NormCityParkData>> getSums(@Valid @RequestBody NormDataVO vo) {
    return normService.getSums(vo);
  }


  @PostMapping("/norms_detail_by_param")
  public List<NormCityParkData> getDetail(@RequestBody NormDataVO vo) {
    return normService.selectDetailNormByParam(vo);
  }

  @PostMapping("/wechat_push")
  public List<WechatPush> getDetail(@RequestBody DetailVO vo) {
    return wechatPushMapper.getDetail(vo);
  }

  @PostMapping("/car_inout_total")
  public List<CarInOutTotal> getCarInOutDetail(@RequestBody DetailVO vo) {
    return carInOutTotalMapper.getCarInOutDetail(vo);
  }

  @PostMapping("/order_info")
  public List<Order> selectOrderList(@RequestBody DetailVO vo) {
    return orderMapper.selectList(vo);
  }

}
