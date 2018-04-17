package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.CarInOutTotal;
import com.cwgj.bigdata.api.data_provider.vo.DetailVO;
import java.util.List;

public interface CarInOutTotalMapper {

  List<CarInOutTotal> getCarInOutDetail(DetailVO vo);
}
