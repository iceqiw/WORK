package com.cwgj.system.mq.api.controller;


import com.cwgj.system.mq.api.inf.MqApiInf;
import com.cwgj.system.mq.api.service.MqttService;
import com.cwgj.system.mq.api.vo.MqDeviceVo;
import com.cwgj.system.mq.api.vo.MqTopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController implements MqApiInf {

  @Autowired
  MqttService mqttService;

  @Override
  public void sender(String content) {
    mqttService.send(content);
  }

  @Override
  public void sender(MqDeviceVo vo) {
    mqttService.send(vo);
  }

  @Override
  public void sender(MqTopicVo vo) {
    mqttService.send(vo);
  }
}
