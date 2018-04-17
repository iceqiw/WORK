package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.Park;

import java.util.List;

public interface ParkMapper {

    List<Park> groupByCity();

    Long getAll();

}
