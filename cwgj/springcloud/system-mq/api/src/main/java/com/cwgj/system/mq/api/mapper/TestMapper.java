package com.cwgj.system.mq.api.mapper;

import com.cwgj.system.mq.api.model.Test;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {


  @Select("select id from t_user_info where id=1")
  Test tt();
}
