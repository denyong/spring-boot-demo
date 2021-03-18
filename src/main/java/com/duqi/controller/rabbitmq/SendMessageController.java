package com.duqi.controller.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dengyong
 * @Date: 2021/02/10/22:56
 * @Description:
 */
@RestController
public class SendMessageController {
  @Autowired
  RabbitTemplate rabbitTemplate;
  @GetMapping("/sendDirectMessage")
  public String sendDirectMessage() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "test message, hello!";
    String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    Map<String,Object> map=new HashMap<>();
    JSONObject map = new JSONObject();
    map.put("messageId","dddddd");
    map.put("messageData","yyyyyykkkkkkkkkkkkkkkkkk");
    map.put("createTime",createTime);
    //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
    rabbitTemplate.convertAndSend("dengyong", "{key:value}");
//    rabbitTemplate.
    return "ok";
  }
}
