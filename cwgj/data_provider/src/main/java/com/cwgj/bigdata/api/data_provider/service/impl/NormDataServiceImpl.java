package com.cwgj.bigdata.api.data_provider.service.impl;

import com.cwgj.bigdata.api.data_provider.mapper.NormDataMapper;
import com.cwgj.bigdata.api.data_provider.model.NormData;
import com.cwgj.bigdata.api.data_provider.service.INormService;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("normDataService")
public class NormDataServiceImpl implements INormService<NormData> {


    @Autowired
    private NormDataMapper normDataMapper;

    @Override
    public List<NormData> getSum(NormDataVO vo) {
        return normDataMapper.selectSumByVO(vo);
    }

    @Override
    public List<NormData> getNormByParam(NormDataVO vo) {
        return normDataMapper.selectListByVO(vo);
    }

    @Override
    public Map<String, List<NormData>> getNormsByParam(NormDataVO vo) {
        Map<String, List<NormData>> resultMap = new HashMap<>();
        for (String event : vo.getEventNames()) {
            vo.setEventName(event);
            List<NormData> list = normDataMapper.selectListByVO(vo);
            resultMap.put(event, list);
        }
        return resultMap;
    }

    @Override
    public Map<String, List<NormData>> getSums(NormDataVO vo) {
        Map<String, List<NormData>> resultMap = new HashMap<>();
        for (String event : vo.getEventNames()) {
            vo.setEventName(event);
            List<NormData> list = normDataMapper.selectSumByVO(vo);
            resultMap.put(event, list);
        }
        return resultMap;
    }

    @Override
    public List<NormData> selectDetailNormByParam(NormDataVO vo) {
        return  normDataMapper.selectListByVO(vo);
    }
}
