package com.cwgj.bigdata.api.data_provider.vo;


import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class NormDataVO extends BaseVO {

    private final static Logger logger = LoggerFactory.getLogger("requestVO");
    private String eventName;
    private String cityContainFlag="pos";
    private String parkContainFlag="pos";
    @NotNull(message = "事件列表不能为空")
    private List<String> eventNames;
    private String startDs;
    private String endDs;
    private List<String> citys;
    private List<String> parks;
    private List<String> mers;
}
