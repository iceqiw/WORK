package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.NormCityParkData;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;

import java.util.List;

public interface NormCityParkDataMapper {

    List<NormCityParkData> selectSumByVO(NormDataVO vo);

    List<NormCityParkData> selectListByVO(NormDataVO vo);

    List<NormCityParkData> selectListDetailByVO(NormDataVO vo);
}
