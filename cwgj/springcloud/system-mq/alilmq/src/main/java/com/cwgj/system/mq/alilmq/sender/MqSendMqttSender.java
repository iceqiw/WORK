package com.cwgj.system.mq.alilmq.sender;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.shade.io.netty.util.internal.StringUtil;
import com.cwgj.system.mq.common.define.MqTopic;
import com.cwgj.system.mq.common.define.MqTopicDefine;
import com.cwgj.system.mq.common.define.QoS;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MQ发送MQTT协议的发送者 Created by linwb on 2017/12/13 0013.
 */
public class MqSendMqttSender {

  private static Logger logger = LoggerFactory.getLogger(MqSendMqttSender.class);

  protected Producer producer;
  private MqTopic parentTopic;
  private String groupId;

  public MqSendMqttSender() {
    parentTopic = MqTopicDefine.parentTopic;
    groupId = MqTopicDefine.groupId;

    Properties initProperties = new Properties();
    initProperties.put(PropertyKeyConst.ProducerId, MqTopicDefine.producerId);
    initProperties.put(PropertyKeyConst.AccessKey, MqTopicDefine.accessKey);
    initProperties.put(PropertyKeyConst.SecretKey, MqTopicDefine.secretKey);
    this.producer = ONSFactory.createProducer(initProperties);
    this.producer.start();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> shutDown()));
  }

  private void shutDown() {
    producer.shutdown();
  }

  private SendResult send(Message message) {
    logger.debug("mqtt send message,:{}", message);
    SendResult result = producer.send(message);
    logger.info("mqtt message {}", result);
    return result;
  }

  private void sendAsync(Message message, SendCallback callback) {
    producer.sendAsync(message, callback);
  }

  private void sendOneway(Message message) {
    producer.sendOneway(message);
  }

  /**
   * 创建消息
   *
   * @param mqttSecondTopic 第二主题
   * @param content 消息内容
   * @param cleanSession 是否清楚会话，值为：true清除会话，即不持久化；值为：false，不清除会话，即持久化
   */
  private Message createMessage(MqTopic mqttSecondTopic, String content, Boolean cleanSession) {
    if (cleanSession == null) {
      cleanSession = false;
    }

    if (StringUtil.isNullOrEmpty(content)) {
      return null;
    }

    Message msg = new Message(
        parentTopic.getTopicName(), // the topic is mqtt parent topic
        "MQ2MQTT", // MQ Tag,must set MQ2MQTT
        content.getBytes());// mqtt msg body
    // sender mormal mqtt msg ,set the property "mqttSecondTopic={{your mqtt
    // subTopic}}"
    if (mqttSecondTopic != null) {
      msg.putUserProperties("mqttSecondTopic", "/" + mqttSecondTopic.getTopicName());
      msg.putUserProperties("qoslevel", String.valueOf(mqttSecondTopic.getQos().getValue()));
    } else {
      // mq sender mqtt msg ,the qos default =1
      msg.putUserProperties("qoslevel",
          String.valueOf(MqTopicDefine.parentTopic.getQos().getValue()));
    }

    // mq sender mqtt msg ,the cleansession default set true
    msg.putUserProperties("cleansessionflag", String.valueOf(cleanSession));

    return msg;
  }

  /**
   * 发送消息
   *
   * @param secondTopic 第二主题
   * @param content 消息内容
   * @param cleanSession MQTT协议中对一个客户端建立 TCP 连接后是否关心之前状态的定义。具体语义如下： 值为true：客户端再次上线时，将不再关心之前所有的订阅关系以及离线消息。值为false：客户端再次上线时，还需要处理之前的离线消息，而之前的订阅关系也会持续生效。
   */
  public SendResult send(MqTopic secondTopic, String content, Boolean cleanSession) {

    Message msg = createMessage(secondTopic, content, cleanSession);
    SendResult sendResult = send(msg);
    return sendResult;
  }


  /**
   * 发送消息(secondTopic的传输服务质量必须为：QoS1，即消息至少达到一次；支持离线消息,即cleanSession=false)
   */
  public SendResult sendBySupportOffline(MqTopic secondTopic, String content) {
    Message msg = createMessage(secondTopic, content, false);
    SendResult sendResult = send(msg);
    return sendResult;
  }

  /**
   * 发送消息(不支持离线消息,即cleanSession=true)
   */
  public SendResult sendByNoSupportOffline(MqTopic secondTopic, String content) {
    Message msg = createMessage(secondTopic, content, true);
    SendResult sendResult = send(msg);
    return sendResult;
  }

  /**
   * 发送消息到设备
   *
   * @param deviceId 设备ID
   */
  public SendResult send2DeviceByNoSupportOffline(String deviceId, String content) {
    return sendByNoSupportOffline(new MqTopic(deviceId, QoS.QoS1), content);
  }

  /**
   * 发送消息到设备(传输服务质量：QoS1，即消息至少达到一次；支持离线消息)
   *
   * @param deviceId 设备ID
   */
  public SendResult send2DeviceBySupportOffline(String deviceId, String content) {
    return sendBySupportOffline(new MqTopic(deviceId, QoS.QoS1), content);
  }
}
