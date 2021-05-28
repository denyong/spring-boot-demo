//package com.duqi.configuration.kafka;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
///**
// * @Auther: dengyong
// * @Date: 2021/03/20/19:39
// * @Description:
// */
//@Component
//@Slf4j
//public class MessageHandler {
//
//  @KafkaListener(topics = "dengyong_topic", containerFactory = "ackContainerFactory")
//  public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
//    try {
//      String message = (String) record.value();
//      log.info("收到消息: {}", message);
//    } catch (Exception e) {
//      log.error(e.getMessage(), e);
//    } finally {
//      // 手动提交 offset
//      acknowledgment.acknowledge();
//    }
//  }
//}