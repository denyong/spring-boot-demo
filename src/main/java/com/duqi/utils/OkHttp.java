package com.duqi.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: dengyong
 * @Date: 2021/03/27/11:27
 * @Description:
 */
public class OkHttp {
  // Content-Type:application/json;charset=UTF-8
  public static final MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
//  public static final String url = "http://127.0.0.1:18902/parseDateFromKafka";
  public static final String url = "http://121.4.227.149:20000/maindata/exportMasterData/searchMasterDataForAPI";
//  public static final String json = "{ 'masterTableName':'organization', 'paramMap':{ 'corp_code':'91320000731789498M' } }";


  public void post() {
    JSONObject json1 = new JSONObject();
    JSONObject json2 = new JSONObject();
    json2.put("corp_code","91320000731789498M");
    json2.put("corp_full_name",null);
    json1.put("masterTableName","organization");
    json1.put("paramMap",json2);
    OkHttpClient okHttpClient = new OkHttpClient();
    RequestBody requestBody = RequestBody.create( String.valueOf(json1),mediaType);



    Request request = new Request.Builder()
        .header("Cookie","JSESSIONID=E64D62725269FB2CE2230E9EF7C0A9BA")
        .header("Accept", "application/json; q=0.5")
        .header("Accept", "*/*")
        .header("Accept-Encoding", "gzip, deflate, br")
        .header("Connection", "keep-alive")
        .url(url).post(requestBody)
        .build();
    try {
      // 使用client去请求
      Response response = okHttpClient.newCall(request).execute();
      if (response.code() == 200) {
        // 获得返回结果
        String result = response.body().string();
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data")
            .getJSONArray("data").getJSONObject(0);

        jsonObject.getString("corp_type");
        jsonObject.getString("corp_full_name");
        jsonObject.getString("corp_code");
        jsonObject.getString("corp_state");
        jsonObject.getString("business_period_from");
        jsonObject.getString("corp_mailbox");
        jsonObject.getString("date_of_approval");
        jsonObject.getString("telephone");
        jsonObject.getString("approval_authority");
        jsonObject.getString("corp_website");
        jsonObject.getString("registered_capital");
        jsonObject.getString("organization_code");
        jsonObject.getString("nature_of_business");
        jsonObject.getString("corp_address");
        jsonObject.getString("corp_industry");
        jsonObject.getString("corp_owner");
        jsonObject.getString("registration_time");
        System.out.println(result);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
//    OkHttp okHttp = new OkHttp();
//    okHttp.post();

//    boolean empty = StringUtils.isEmpty(" ");

//    User build = User.builder().id(12).id1(345).build();

    JSONObject data = JSONObject.parseObject("").getJSONObject("data")
        .getJSONArray("data").getJSONObject(0);

    System.out.println(data.getString("123"));


  }
}

@Builder
@AllArgsConstructor
@NoArgsConstructor
class User{
  public int id;
  public int id1;
  public void setId(int id){
    this.id = id;
  }

  public int getId(){
    return this.id;
  }

  public void setId1(int id1){
    this.id1 = id1;
  }

  public int getId1(){
    return this.id1;
  }
}
