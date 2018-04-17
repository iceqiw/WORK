package com.cwgj.system.mq.api.inf;


import com.cwgj.system.mq.api.vo.MqDeviceVo;
import com.cwgj.system.mq.api.vo.MqTopicVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("system-mq")
@RequestMapping("/mq")
public interface MqApiInf {

  @PostMapping(value = "/sender")
  void sender(@RequestBody String content);

  @PostMapping(value = "/device/sender")
  void sender(@RequestBody MqDeviceVo vo);

  @PostMapping(value = "/topic/sender")
  void sender(@RequestBody MqTopicVo vo);

}
