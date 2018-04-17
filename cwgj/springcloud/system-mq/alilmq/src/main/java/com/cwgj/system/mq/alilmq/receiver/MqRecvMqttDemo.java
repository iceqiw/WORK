package com.cwgj.system.mq.alilmq.receiver;

import com.aliyun.openservices.ons.api.Action;

/**
 * Created by alvin on 17-7-24. This is simple example for mq java client recv mqtt msg
 */
public class MqRecvMqttDemo {

  public static void main(String[] args) throws Exception {
    MqRecvMqttReceiver receiver = new MqRecvMqttReceiver();

    receiver.process((message, context) -> {
      System.out.println("recv msg:" + message);
      System.out.println(new String(message.getBody()));
      return Action.CommitMessage;
    });
  }

}
