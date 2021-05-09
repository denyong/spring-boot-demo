package com.duqi.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Component
@Slf4j
public class KafkaSender {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String message) {

    ListenableFuture<SendResult<String, String>> sender = kafkaTemplate
        .send(new ProducerRecord<>(topic, message));
    //发送成功
    SuccessCallback successCallback = result -> log.info("数据发送成功!");
    //发送失败回调
    FailureCallback failureCallback = ex -> log.error("数据发送失败!");

    sender.addCallback(result -> {
    }, ex -> log.error("数据发送失败!"));
  }
}
