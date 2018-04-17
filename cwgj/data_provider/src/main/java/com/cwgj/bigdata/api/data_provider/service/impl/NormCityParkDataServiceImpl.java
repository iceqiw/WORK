package com.cwgj.bigdata.api.data_provider.service.impl;

import com.cwgj.bigdata.api.data_provider.mapper.NormCityParkDataMapper;
import com.cwgj.bigdata.api.data_provider.model.NormCityParkData;
import com.cwgj.bigdata.api.data_provider.model.NormData;
import com.cwgj.bigdata.api.data_provider.service.INormService;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("normCityParkDataService")
public class NormCityParkDataServiceImpl implements INormService<NormCityParkData> {


    @Autowired
    private NormCityParkDataMapper normCityParkDataMapper;

    @Override
    public List<NormCityParkData> getSum(NormDataVO vo) {
        return normCityParkDataMapper.selectSumByVO(vo);
    }

    @Override
    public List<NormCityParkData> getNormByParam(NormDataVO vo) {
        return normCityParkDataMapper.selectListByVO(vo);
    }

    @Override
    public List<NormCityParkData> selectDetailNormByParam(NormDataVO vo) {
        return normCityParkDataMapper.selectListDetailByVO(vo);
    }

    @Override
    public Map<String, List<NormCityParkData>> getNormsByParam(NormDataVO vo) {
        Map<String, List<NormCityParkData>> resultMap = new HashMap<>();
        for (String event : vo.getEventNames()) {
            vo.setEventName(event);
            List<NormCityParkData> list = normCityParkDataMapper.selectListByVO(vo);
            resultMap.put(event, list);
        }
        return resultMap;
    }

    @Override
    public Map<String, List<NormCityParkData>> getSums(NormDataVO vo) {
        Map<String, List<NormCityParkData>> resultMap = new HashMap<>();
        for (String event : vo.getEventNames()) {
            vo.setEventName(event);
            List<NormCityParkData> list = normCityParkDataMapper.selectSumByVO(vo);
            resultMap.put(event, list);
        }
        return resultMap;
    }
}
