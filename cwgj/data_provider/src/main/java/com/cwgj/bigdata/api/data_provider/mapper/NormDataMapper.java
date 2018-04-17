package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.NormData;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;

import java.util.List;

public interface NormDataMapper {

    List<NormData> selectSumByVO(NormDataVO vo);

    List<NormData> selectListByVO(NormDataVO vo);
}
