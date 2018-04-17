package com.cwgj.bigdata.api.data_provider.service;

import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;

import java.util.List;
import java.util.Map;

public interface INormService<T> {

    List<T> getSum(NormDataVO vo);

    List<T> getNormByParam(NormDataVO vo);

    List<T> selectDetailNormByParam(NormDataVO vo);

    Map<String, List<T>> getNormsByParam(NormDataVO vo);

    Map<String, List<T>> getSums(NormDataVO vo);
}
