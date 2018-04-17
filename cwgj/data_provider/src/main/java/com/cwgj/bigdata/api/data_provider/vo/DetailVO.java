package com.cwgj.bigdata.api.data_provider.vo;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class DetailVO extends BaseVO {

    private final static Logger logger = LoggerFactory.getLogger("DetailVO");
    private String cityContainFlag="pos";
    private String parkContainFlag="pos";
    private String startDs;
    private String endDs;
    private List<String> citys;
    private List<String> parks;
    private List<String> mers;
}
