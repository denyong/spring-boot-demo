package com.test.rabbitmq;

import static com.alibaba.fastjson.JSON.parseObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.beanutils.ConvertUtils;

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
