package com.cwgj.system.sms.api.inf;


import com.cwgj.system.sms.api.vo.CodeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("system-push")
@RequestMapping("/sms")
public interface SmsApiInf {

  @PostMapping("/login")
  boolean login(@RequestBody CodeVo vo) throws Exception;

  @PostMapping("/register")
  boolean register(@RequestBody CodeVo vo) throws Exception;
}
