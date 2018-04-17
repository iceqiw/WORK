package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.model.Park;
import com.cwgj.bigdata.api.data_provider.service.impl.CommonServiceImpl;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {


    @Autowired
    private CommonServiceImpl commonService;

    @PostMapping("/get_park_by_city")
    public List<Park> getParkByCity(@RequestBody NormDataVO vo) {
        return commonService.getParkCountByCity();
    }

    @PostMapping("/park_count")
    public Long getParkCount(@RequestBody NormDataVO vo) {
        return commonService.getAllParkCount();
    }

}
