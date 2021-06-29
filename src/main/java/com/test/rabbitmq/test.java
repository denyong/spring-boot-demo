package com.test.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author : dengyong
 * @description :
 * @createTime : 2021/5/8 14:07
 */
public class test {

  public static void main(String[] args) {
    String url = "http://localhost/test";
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url,JSONObject.class);
    JSONObject forObject = restTemplate.getForObject(url, JSONObject.class);
    System.out.println(entity);
    System.out.println(forObject);
    System.out.println(123);
  }
}
