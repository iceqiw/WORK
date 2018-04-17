package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.NormBussData;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;

import java.util.List;

public interface NormBussDataMapper {

    List<NormBussData> selectSumByVO(NormDataVO vo);

    List<NormBussData> selectListByVO(NormDataVO vo);

    List<NormBussData> selectListDetailByVO(NormDataVO vo);
}
