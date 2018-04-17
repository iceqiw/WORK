package com.cwgj.system.mq.api.service;


import com.cwgj.system.mq.alilmq.sender.MqSendMqttSender;
import com.cwgj.system.mq.api.vo.MqDeviceVo;
import com.cwgj.system.mq.api.vo.MqTopicVo;
import com.cwgj.system.mq.common.define.MqTopic;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class MqttService {


  MqSendMqttSender sendMqttSender;


  @PostConstruct
  public void init() {
    sendMqttSender = new MqSendMqttSender();
  }

  public void send(String content) {
    sendMqttSender.send(new MqTopic("defaut"), content, true);
  }

  public void send(MqTopicVo vo) {
    sendMqttSender.send(new MqTopic(vo.getTopic()), vo.getContent(), true);
  }

  public void send(MqDeviceVo vo) {
    sendMqttSender.send2DeviceBySupportOffline(vo.getDeviceId(), vo.getContent());
  }
}
