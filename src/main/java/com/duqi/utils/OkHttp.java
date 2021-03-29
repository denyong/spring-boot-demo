package com.duqi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Auther: dengyong
 * @Date: 2021/03/27/11:27
 * @Description:
 */
public class OkHttp {

  public static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
  public static final String url = "http://localhost:8881/okhttp2";
  public static final String json = "{'a':'aa'}";

  public void post() {
    OkHttpClient okHttpClient = new OkHttpClient();
    RequestBody requestBody = RequestBody.create(json, mediaType);
    Request request = new Request.Builder().addHeader("name","value").url(url).post(requestBody)
        .build();
    try {
      // 使用client去请求
      Response response = okHttpClient.newCall(request).execute();
      // 获得返回结果
      String result = response.body().string();
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    OkHttp okHttp = new OkHttp();
    okHttp.post();
  }
}
