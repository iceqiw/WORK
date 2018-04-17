package com.cwgj.system.sms.api.mapper;

import com.cwgj.system.sms.api.model.Test;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {


  @Select("select id from t_user_info where id=1")
  Test tt();
}
