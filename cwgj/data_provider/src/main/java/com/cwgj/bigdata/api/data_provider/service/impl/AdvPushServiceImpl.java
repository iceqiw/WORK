package com.cwgj.bigdata.api.data_provider.service.impl;


import com.cwgj.bigdata.api.data_provider.mapper.AdvPushMapper;
import com.cwgj.bigdata.api.data_provider.model.AdvAll;
import com.cwgj.bigdata.api.data_provider.model.AdvDetail;
import com.cwgj.bigdata.api.data_provider.model.AdvTotalByDs;
import com.cwgj.bigdata.api.data_provider.vo.AdvVO;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("advPushService")
public class AdvPushServiceImpl {

  @Resource
  private AdvPushMapper advPushMapper;

  public List<AdvDetail> getAdvDetail(AdvVO vo) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.MILLISECOND, 0);
    vo.setTimestamp(String.valueOf(cal.getTimeInMillis()));
    return advPushMapper.getAdvDetail(vo);
  }

  public List<AdvAll> getAdvTotalPushType(AdvVO vo) {
    return advPushMapper.getAdvTotalPushType(vo);
  }

  public List<AdvAll> getAdvAllByAdvId(AdvVO vo) {
    return advPushMapper.getAdvAllByAdvId(vo);
  }

  public List<AdvTotalByDs> getAdvListGroupByDs(AdvVO vo) {
    return advPushMapper.getAdvListGroupByDs(vo);
  }

}
