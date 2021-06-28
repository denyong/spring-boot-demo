//package com.duqi.controller.rabbitmq;
//
//import com.duqi.configuration.kafka.KafkaSender;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Auther: dengyong
// * @Date: 2021/02/15/22:03
// * @Description:
// */
//@RestController
//public class TestA {
//  @Autowired
//  private KafkaSender kafkaSender;
//
//  @Autowired
//  private KafkaTemplate<String, String> kafkaTemplate;
//
//  @PostMapping("/test1")
//  public ResponseEntity<Void> test11(@RequestParam("id") String id){
//    kafkaSender.sendMessage("dengyong_topic","kafkaSender.sendMessage");
//    kafkaTemplate.send("dengyong_topic", "kafkaTemplate.send");
//
//    return ResponseEntity.ok().build();
//  }
//}
