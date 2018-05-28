package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.AdvAll;
import com.cwgj.bigdata.api.data_provider.model.AdvDetail;
import com.cwgj.bigdata.api.data_provider.model.AdvTotalByDs;
import com.cwgj.bigdata.api.data_provider.vo.AdvVO;
import java.util.List;

public interface AdvPushMapper {

  List<AdvDetail> getAdvDetail(AdvVO vo);

  List<AdvAll> getAdvTotalPushType(AdvVO vo);

  List<AdvAll> getAdvAllByAdvId(AdvVO vo);

  List<AdvTotalByDs> getAdvListGroupByDs(AdvVO vo);

  List<AdvDetail> getAdvDetailSize(AdvVO vo);

  Long getAdvDetailCount(AdvVO vo);
}
