package com.cwgj.system.sms.api.mapper;


import com.cwgj.system.sms.common.model.SmsTempCode;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface SmsTempCodeMapper {


  @Select("select templete_code as templeteCode,description,status from t_sms_temp_code where status=1")
  List<SmsTempCode> getInfo();
}
