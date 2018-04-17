package com.cwgj.system.sms.api.controller;


import com.aliyuncs.exceptions.ClientException;
import com.cwgj.system.sms.alisms.handler.AliSmsHandler;
import com.cwgj.system.sms.alisms.handler.VerificationCodeTypeConstant;
import com.cwgj.system.sms.api.inf.SmsApiInf;
import com.cwgj.system.sms.api.vo.CodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController implements SmsApiInf {


  @Autowired
  AliSmsHandler aliSmsHandler;


  @Override
  public boolean login(@RequestBody CodeVo vo) throws ClientException {
    return aliSmsHandler
        .sendVerificationCodeSms(VerificationCodeTypeConstant.LOGIN, vo.getTelphone(), vo.getCode())
        .isSuccess();
  }

  @Override
  public boolean register(@RequestBody CodeVo vo) throws ClientException {
    return aliSmsHandler
        .sendVerificationCodeSms(VerificationCodeTypeConstant.REGISTER, vo.getTelphone(),
            vo.getCode()).isSuccess();
  }

}
