package com.cwgj.system.sms.alisms.handler;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cwgj.system.sms.alisms.AliSmsClient;
import com.cwgj.system.sms.common.dto.SmsResult;


public class AliSmsHandler {


  AliSmsClient aliSmsClient;

  public AliSmsClient getAliSmsClient() {
    return aliSmsClient;
  }

  public void setAliSmsClient(AliSmsClient aliSmsClient) {
    this.aliSmsClient = aliSmsClient;
  }


  public SmsResult smsAliyun(String templeteCode, String telephone, String content) {

    SendSmsResponse sendSmsResponse = smsAliyunMsg(templeteCode, telephone, content);

    if (sendSmsResponse == null) {
      return new SmsResult("999", "抱歉，您短信下发次数太多，请稍后再试，谢谢。");
    }
    if (sendSmsResponse.getMessage().equals("OK")) {
      return new SmsResult();
    }
    return new SmsResult(sendSmsResponse.getMessage(), sendSmsResponse.getCode());
  }

  private SendSmsResponse smsAliyunMsg(String templeteCode, String telephone, String content) {
    try {
      return aliSmsClient.send(templeteCode, content, telephone);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return null;
  }
}
