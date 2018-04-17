package com.cwgj.system.mq.alilmq.receiver;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.cwgj.system.mq.common.define.MqTopicDefine;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alvin on 17-7-24. This is simple example for mq java client recv mqtt msg
 */
public class MqRecvMqttReceiver {

  private static Logger logger = LoggerFactory.getLogger(MqRecvMqttReceiver.class);

  protected Consumer consumer;

  MessageListener listener;


  public MqRecvMqttReceiver() {
    Properties initProperties = new Properties();
    initProperties.put(PropertyKeyConst.ConsumerId, MqTopicDefine.consumerId);
    initProperties.put(PropertyKeyConst.AccessKey, MqTopicDefine.accessKey);
    initProperties.put(PropertyKeyConst.SecretKey, MqTopicDefine.secretKey);
    consumer = ONSFactory.createConsumer(initProperties);
  }

  public void start() {
    consumer.start();
    logger.info("start");
    Runtime.getRuntime().addShutdownHook(new Thread(() -> shutDown()));
  }

  public void shutDown() {
    consumer.shutdown();
  }


  public void listener(MessageListener messageListener) {
    this.listener = messageListener;
  }

  public void subscribe(String subExpression) {
    consumer.subscribe(MqTopicDefine.parentTopic.getTopicName(), subExpression, listener);
  }

  public void subscribe() {
    consumer.subscribe(MqTopicDefine.parentTopic.getTopicName(), "*", listener);
  }

  public void process(String subExpression, MessageListener messageListener) {
    listener(messageListener);
    subscribe(subExpression);
    consumer.start();
    logger.info("start");
    Runtime.getRuntime().addShutdownHook(new Thread(() -> shutDown()));
  }

  public void process(MessageListener messageListener) {
    listener(messageListener);
    subscribe();
    consumer.start();
    logger.info("start");
    Runtime.getRuntime().addShutdownHook(new Thread(() -> shutDown()));
  }

}
