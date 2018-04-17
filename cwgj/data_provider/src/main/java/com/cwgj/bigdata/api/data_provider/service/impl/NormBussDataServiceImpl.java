package com.cwgj.bigdata.api.data_provider.service.impl;

import com.cwgj.bigdata.api.data_provider.mapper.NormBussDataMapper;
import com.cwgj.bigdata.api.data_provider.model.NormBussData;
import com.cwgj.bigdata.api.data_provider.service.INormService;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("normBussDataService")
public class NormBussDataServiceImpl implements INormService<NormBussData> {


    @Autowired
    private NormBussDataMapper normBussDataMapper;

    @Override
    public List<NormBussData> getSum(NormDataVO vo) {
        return normBussDataMapper.selectSumByVO(vo);
    }

    @Override
    public List<NormBussData> getNormByParam(NormDataVO vo) {
        return normBussDataMapper.selectListByVO(vo);
    }

    @Override
    public List<NormBussData> selectDetailNormByParam(NormDataVO vo) {
        return normBussDataMapper.selectListDetailByVO(vo);
    }

    @Override
    public Map<String, List<NormBussData>> getNormsByParam(NormDataVO vo) {
        Map<String, List<NormBussData>> resultMap = new HashMap<>();
        for (String event : vo.getEventNames()) {
            vo.setEventName(event);
            List<NormBussData> list = normBussDataMapper.selectListByVO(vo);
            resultMap.put(event, list);
        }
        return resultMap;
    }

    @Override
    public Map<String, List<NormBussData>> getSums(NormDataVO vo) {
        Map<String, List<NormBussData>> resultMap = new HashMap<>();
        for (String event : vo.getEventNames()) {
            vo.setEventName(event);
            List<NormBussData> list = normBussDataMapper.selectSumByVO(vo);
            resultMap.put(event, list);
        }
        return resultMap;
    }
}
