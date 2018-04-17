package com.cwgj.system.sms.inf;


import com.cwgj.system.sms.common.vo.SmsTempCodeVo;
import com.cwgj.system.sms.common.vo.SmsVo;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface SmsApiInf {

  @PostMapping("/sendVerifyCode")
  boolean sendVerifyCode(SmsVo vo) throws Exception;

  @PostMapping("/notify")
  boolean sendSMSNotification(SmsVo vo) throws Exception;

  @PostMapping("/sms")
  boolean sendSMSForm(String templeteCode, String content, String telphone, String site)
      throws Exception;

  @GetMapping("/tempInfo")
  List<SmsTempCodeVo> tempInfo();
}
