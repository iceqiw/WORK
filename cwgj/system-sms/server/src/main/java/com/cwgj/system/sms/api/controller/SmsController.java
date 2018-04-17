package com.cwgj.system.sms.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.cwgj.system.sms.alisms.handler.AliSmsHandler;
import com.cwgj.system.sms.api.mapper.SmsTempCodeMapper;
import com.cwgj.system.sms.common.mapstruct.SmsTempCodeMs;
import com.cwgj.system.sms.common.model.SmsTempCode;
import com.cwgj.system.sms.common.vo.SmsTempCodeVo;
import com.cwgj.system.sms.common.vo.SmsVo;
import com.cwgj.system.sms.inf.SmsApiInf;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController implements SmsApiInf {


  private static Logger logger = LoggerFactory.getLogger(SmsController.class);


  @Autowired
  AliSmsHandler aliSmsHandler;

  @Autowired
  SmsTempCodeMapper smsTempCodeMapper;


  @Autowired
  SmsTempCodeMs smsTempCodeMs;

  @Override
  public boolean sendVerifyCode(@RequestBody SmsVo vo) throws Exception {
    JSONObject jo = new JSONObject();
    jo.put("code", vo.getContent());
    return aliSmsHandler.smsAliyun(vo.getTempleteCode(), vo.getTelphone(), jo.toString())
        .isSuccess();
  }

  @Override
  public boolean sendSMSNotification(@RequestBody SmsVo vo) throws Exception {
    return aliSmsHandler.smsAliyun(vo.getTempleteCode(), vo.getTelphone(), vo.getContent())
        .isSuccess();
  }

  @Override
  public boolean sendSMSForm(String templeteCode, String content, String telphone,
      String site) throws Exception {
    logger.info("access: templeteCode="+templeteCode+",content="+content+",telphone="+telphone+",site="+site);
    return aliSmsHandler.smsAliyun(templeteCode, telphone, content)
        .isSuccess();
  }

  @Override
  public List<SmsTempCodeVo> tempInfo() {
    List<SmsTempCode> list = smsTempCodeMapper.getInfo();
    List<SmsTempCodeVo> vos = smsTempCodeMs.smsTempCodeToSmsTempCodeVo(list);
    return vos;
  }

}
