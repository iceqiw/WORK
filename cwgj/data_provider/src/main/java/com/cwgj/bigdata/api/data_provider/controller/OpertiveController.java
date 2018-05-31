package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.mapper.OpertiveReportMapper;
import com.cwgj.bigdata.api.data_provider.model.OpertiveReport;
import com.cwgj.bigdata.api.data_provider.model.OpertiveReportAllSum;
import com.cwgj.bigdata.api.data_provider.model.OpertiveReportSum;
import com.cwgj.bigdata.api.data_provider.vo.DetailVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opertive")
public class OpertiveController {

  @Resource
  private OpertiveReportMapper opertiveReportMapper;


  @PostMapping("/report")
  public List<OpertiveReport> selectOrderList(@RequestBody DetailVO vo) {
    return opertiveReportMapper.getReportDetail(vo);
  }

  @PostMapping("/reportSum")
  public List<OpertiveReportSum> selectOrderSum(@RequestBody DetailVO vo) {
    return opertiveReportMapper.getReportDetailSum(vo);
  }

  @PostMapping("/allSum")
  public List<OpertiveReportAllSum> selectAllSum(@RequestBody DetailVO vo) {
    return opertiveReportMapper.getReportAllSum(vo);
  }

  @PostMapping("/total")
  public OpertiveReportAllSum selectSumAllNoGroup() {
    return opertiveReportMapper.getAllSum();
  }
}
