package com.cwgj.bigdata.api.data_provider.controller;


import com.cwgj.bigdata.api.data_provider.model.NormData;
import com.cwgj.bigdata.api.data_provider.service.INormService;
import com.cwgj.bigdata.api.data_provider.vo.NormDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/normal")
public class NormalController {


    @Autowired
    @Qualifier("normDataService")
    private INormService normService;

    @PostMapping("/norms_by_param")
    public List<NormData> getNormsByDs(@Valid @RequestBody NormDataVO vo) {
        return normService.getNormByParam(vo);
    }

    @PostMapping("/norms_sum_by_param")
    public Map<String, List<NormData>> getSums(@Valid @RequestBody NormDataVO vo) {
        return normService.getSums(vo);
    }

}
