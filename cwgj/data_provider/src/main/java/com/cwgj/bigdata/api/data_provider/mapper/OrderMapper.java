package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.Order;
import com.cwgj.bigdata.api.data_provider.vo.DetailVO;
import java.util.List;

public interface OrderMapper {

  List<Order> selectList(DetailVO vo);

}
