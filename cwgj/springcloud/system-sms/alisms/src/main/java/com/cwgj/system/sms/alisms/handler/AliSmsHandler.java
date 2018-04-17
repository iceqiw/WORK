package com.cwgj.system.sms.alisms.handler;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cwgj.system.sms.alisms.AliSmsClient;


public class AliSmsHandler {


  AliSmsClient aliSmsClient;

  public AliSmsClient getAliSmsClient() {
    return aliSmsClient;
  }

  public void setAliSmsClient(AliSmsClient aliSmsClient) {
    this.aliSmsClient = aliSmsClient;
  }


  public SmsResult sendVerificationCodeSms(int type, String telephone, String code) {
    String templeteCode = getTempleteCode(type);
    if (templeteCode == null) {// 不存在的发送短信类型，可能是攻击
      return new SmsResult("999", "不存在的发送短信类型");
    }

    SendSmsResponse sendSmsResponse = sendVerificationCodeSms(templeteCode, telephone, code);

    if (sendSmsResponse == null) {
      return new SmsResult("999", "抱歉，您短信下发次数太多，请稍后再试，谢谢。");
    }
    if (sendSmsResponse.getMessage().equals("OK")) {
      return new SmsResult();
    }
    return new SmsResult(sendSmsResponse.getMessage(), sendSmsResponse.getCode());
  }


  public SmsResult smsAliyunNotify(int type, String telephone, JSONObject jsonObject) {
    String templeteCode = getTempleteCode(type);
    if (templeteCode == null) {// 不存在的发送短信类型，可能是攻击
      return new SmsResult("999", "不存在的发送短信类型");
    }
    SendSmsResponse sendSmsResponse = smsAliyunNotify(templeteCode, telephone, jsonObject);
    if (sendSmsResponse == null) {
      return new SmsResult("999", "抱歉，您短信下发次数太多，请稍后再试，谢谢。");
    }
    if (sendSmsResponse.getMessage().equals("OK")) {
      return new SmsResult();
    }
    return new SmsResult(sendSmsResponse.getMessage(), sendSmsResponse.getCode());
  }

  private SendSmsResponse sendVerificationCodeSms(String templeteCode, String telephone,
      String code) {
    JSONObject jo = new JSONObject();
    jo.put("code", code);
    try {
      return aliSmsClient.send(templeteCode, jo.toJSONString(), telephone);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return null;
  }


  private SendSmsResponse smsAliyunNotify(String templeteCode, String telephone,
      JSONObject jsonObject) {
    try {
      return aliSmsClient
          .send(templeteCode, jsonObject.toJSONString(), telephone);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return null;
  }


  private String getTempleteCode(int type) {
    String code = null;
    switch (type) {
      case VerificationCodeTypeConstant.REGISTER:
        code = "SMS_41645326";
        break;
      case VerificationCodeTypeConstant.ACTIVATE:
        break;
      case VerificationCodeTypeConstant.TAKE_BACK_PWD:
        break;
      case VerificationCodeTypeConstant.LOGIN:
        code = "SMS_41625280";
        break;
      case VerificationCodeTypeConstant.MODIFY_LOGIN_PWD:
        code = "SMS_18265167";
        break;
      case VerificationCodeTypeConstant.MODIFY_TRADE_PWD:
        break;
      case VerificationCodeTypeConstant.WXGZH_LOGIN:
        code = "SMS_41625280";
        break;
      case VerificationCodeTypeConstant.PALM_PARKING_LOGIN:
        code = "SMS_122297626";
        break;
      case VerificationCodeTypeConstant.MACHINE_STOPPAGE:
        code = "SMS_126352661";
        break;
      case VerificationCodeTypeConstant.RECONNECT_SUCCESS:
        code = "SMS_126352654";
        break;
      case VerificationCodeTypeConstant.DISCONNECT:
        code = "SMS_126357468";
        break;

    }
    return code;
  }
}
