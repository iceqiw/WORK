package com.cwgj.system.sms.alisms;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import javax.annotation.PostConstruct;


public class AliSmsClient {

  private IAcsClient client;

  private String signName;
  private String connectTimeout;
  private String readTimeout;
  private String regionid;
  private String accessKeyId;
  private String accessKeySecret;
  private String product;
  private String domain;
  private String endpointName;


  public SendSmsResponse send(String templateCode, String templateParams, String phones)
      throws ClientException {

    SendSmsRequest request = new SendSmsRequest();
    // 必填:短信签名-可在短信控制台中找到
    request.setSignName(signName);
    // 必填:短信模板-可在短信控制台中找到
    request.setTemplateCode(templateCode);
    // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
    request.setTemplateParam(templateParams);
    // 必填:待发送手机号
    request.setPhoneNumbers(phones);
    // hint 此处可能会抛出异常，注意catch
    SendSmsResponse sendSmsResponse = client.getAcsResponse(request);

    return sendSmsResponse;
  }

  @PostConstruct
  private void init() throws ClientException {
    System.setProperty("sun.net.client.defaultConnectTimeout", connectTimeout);
    System.setProperty("sun.net.client.defaultReadTimeout", readTimeout);
    IClientProfile profile = DefaultProfile.getProfile(regionid, accessKeyId, accessKeySecret);
    DefaultProfile.addEndpoint(endpointName, regionid, product, domain);
    this.client = new DefaultAcsClient(profile);
  }


  public AliSmsClient(String signName, String connectTimeout, String readTimeout,
      String regionid, String accessKeyId, String accessKeySecret, String product,
      String domain, String endpointName) {
    this.signName = signName;
    this.connectTimeout = connectTimeout;
    this.readTimeout = readTimeout;
    this.regionid = regionid;
    this.accessKeyId = accessKeyId;
    this.accessKeySecret = accessKeySecret;
    this.product = product;
    this.domain = domain;
    this.endpointName = endpointName;
  }

  public AliSmsClient(String signName, String accessKeyId, String accessKeySecret) {
    this.signName = signName;
    this.connectTimeout = "10000";
    this.readTimeout = "10000";
    this.regionid = "cn-hangzhou";
    this.accessKeyId = accessKeyId;
    this.accessKeySecret = accessKeySecret;
    this.product = "Dysmsapi";
    this.domain = "dysmsapi.aliyuncs.com";
    this.endpointName = "cn-hangzhou";
  }

  public AliSmsClient() {
    this.connectTimeout = "10000";
    this.readTimeout = "10000";
    this.regionid = "cn-hangzhou";
    this.product = "Dysmsapi";
    this.domain = "dysmsapi.aliyuncs.com";
    this.endpointName = "cn-hangzhou";
  }

}

