package com.cwgj.system.sms.common.mapstruct;


import com.cwgj.system.sms.common.model.SmsTempCode;
import com.cwgj.system.sms.common.vo.SmsTempCodeVo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SmsTempCodeMs {

  SmsTempCodeVo smsTempCodeToSmsTempCodeVo(SmsTempCode o);

  List<SmsTempCodeVo> smsTempCodeToSmsTempCodeVo(List<SmsTempCode> list);
}
