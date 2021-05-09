package com.test.rabbitmq;

import static com.alibaba.fastjson.JSON.parseObject;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @author : dengyong
 * @description :
 * @createTime : 2021/5/8 14:07
 */
public class test {

  public static void main(String[] args) {
    RestTemplate restTemplate = new RestTemplate();
    JSONObject childJson = new JSONObject();
    childJson.put("corp_full_name", "DHL空运服务（上海）有限公司");
    JSONObject json = new JSONObject();
    json.put("masterTableName", "organization");
    json.put("paramMap", childJson);
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    String body = restTemplate
        .exchange("http://121.4.227.149:20000/maindata/exportMasterData/searchMasterDataForAPI", HttpMethod.POST, new HttpEntity<>(json, headers), JSONObject.class).getBody()
        .toJSONString();
    // 若存在客户相关信息，则说明经过工商认证
    boolean isGongShangCheck = (body != null && parseObject(body).getJSONObject("data")
        .getJSONArray("data").size() > 0);
    // 1. 获取客户信息
    System.out.println(isGongShangCheck);
  }
}
