package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.model.NormBussData;
import com.cwgj.bigdata.api.data_provider.service.INormService;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/normalbuss")
public class NormalByBussController {


    @Autowired
    @Qualifier("normBussDataService")
    private INormService normService;

    @PostMapping("/norms_by_param")
    public Map<String, List<NormBussData>> getNormsByDs(@Valid @RequestBody NormDataVO vo) {
        return normService.getNormsByParam(vo);
    }

    @PostMapping("/norms_sum_by_param")
    public Map<String, List<NormBussData>> getSums(@Valid @RequestBody NormDataVO vo) {
        return normService.getSums(vo);
    }


    @PostMapping("/norms_detail_by_param")
    public List<NormBussData> getDetail(@RequestBody NormDataVO vo) {
        return normService.selectDetailNormByParam(vo);
    }

}
