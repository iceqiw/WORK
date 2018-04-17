package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.OpertiveReport;
import com.cwgj.bigdata.api.data_provider.model.OpertiveReportAllSum;
import com.cwgj.bigdata.api.data_provider.model.OpertiveReportSum;
import com.cwgj.bigdata.api.data_provider.vo.DetailVO;
import java.util.List;

public interface OpertiveReportMapper {

  List<OpertiveReport> getReportDetail(DetailVO vo);

  List<OpertiveReportSum> getReportDetailSum(DetailVO vo);

  List<OpertiveReportAllSum> getReportAllSum(DetailVO vo);
}
