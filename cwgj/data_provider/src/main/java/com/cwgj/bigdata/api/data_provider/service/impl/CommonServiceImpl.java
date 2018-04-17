package com.cwgj.bigdata.api.data_provider.service.impl;


import com.cwgj.bigdata.api.data_provider.mapper.ParkMapper;
import com.cwgj.bigdata.api.data_provider.model.Park;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commonService")
public class CommonServiceImpl {

    @Resource
    private ParkMapper parkMapper;

    @Cacheable(value = "Park", key = "'getParkCountByCity'")
    public List<Park> getParkCountByCity() {
        return parkMapper.groupByCity();
    }

    @Cacheable(value = "Park", key = "'getAllParkCount'")
    public Long getAllParkCount() {
        return parkMapper.getAll();
    }

}
