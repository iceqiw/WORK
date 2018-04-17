package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.RealDayStatData;
import com.cwgj.bigdata.api.data_provider.model.RealStatData;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import java.util.List;

public interface RealStatMapper {


  List<RealStatData> allStat();

  List<RealDayStatData> allDayStat(NormDataVO vo);
}
