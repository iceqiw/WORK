package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.mapper.RealStatMapper;
import com.cwgj.bigdata.api.data_provider.model.RealDayStatData;
import com.cwgj.bigdata.api.data_provider.model.RealStatData;
import com.cwgj.bigdata.api.data_provider.vo.BaseVO;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realstat")
public class RealStatController {


  @Resource
  private RealStatMapper realStatMapper;


  @PostMapping("/all")
  public List<RealStatData> selectOrderList(@RequestBody BaseVO vo) {
    return realStatMapper.allStat();
  }

  @PostMapping("/all_day")
  public List<RealDayStatData> selectOrderList(@RequestBody NormDataVO vo) {
    return realStatMapper.allDayStat(vo);
  }
}
