package com.cwgj.system.mq.alilmq.sender;

import com.cwgj.system.mq.common.define.MqTopic;
import com.cwgj.system.mq.common.define.QoS;

/**
 * Created by alvin on 17-7-24. This is simple example for mqtt async java client sender mqtt msg
 */
public class MqttAsyncSimpleSendDemo {

  public static void main(String[] args) throws Exception {
    MqSendMqttSender sender = new MqSendMqttSender();
    sender.send(new MqTopic("0591", QoS.QoS1), "0591059105910591059105910591", true);
  }
}
