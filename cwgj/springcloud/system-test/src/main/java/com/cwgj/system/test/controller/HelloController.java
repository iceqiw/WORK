package com.cwgj.system.test.controller;


import com.cwgj.system.push.api.inf.SmsApiInf;
import com.cwgj.system.push.api.vo.CodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

  @Autowired
  SmsApiInf smsApiInf;


  @RequestMapping("/a")
  public void helloWorld() {
    try {
      CodeVo vo = new CodeVo("1123", "13075862311", "111");
      smsApiInf.login(vo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
