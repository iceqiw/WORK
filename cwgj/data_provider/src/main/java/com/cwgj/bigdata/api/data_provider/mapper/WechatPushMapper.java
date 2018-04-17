package com.cwgj.bigdata.api.data_provider.mapper;

import com.cwgj.bigdata.api.data_provider.model.WechatPush;
import com.cwgj.bigdata.api.data_provider.vo.DetailVO;
import java.util.List;

public interface WechatPushMapper {

  List<WechatPush> getDetail(DetailVO vo);

}
