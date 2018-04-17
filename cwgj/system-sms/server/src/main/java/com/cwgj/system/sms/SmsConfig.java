package com.cwgj.system.sms;


import com.cwgj.system.sms.alisms.AliSmsClient;
import com.cwgj.system.sms.alisms.handler.AliSmsHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {

  @Bean
  public AliSmsClient aliSmsClient(@Value("${ali.signName}") String signName,
      @Value("${ali.accessKeyId}") String accessKeyId,
      @Value("${ali.accessKeySecret}") String accessKeySecret) throws Exception {
    return new AliSmsClient(signName, accessKeyId, accessKeySecret);
  }

  @Bean
  public AliSmsHandler aliSmsHandler(AliSmsClient aliSmsClient) throws Exception {
    AliSmsHandler aliSmsHandler = new AliSmsHandler();
    aliSmsHandler.setAliSmsClient(aliSmsClient);
    return aliSmsHandler;
  }

}
