package com.duqi;

import com.alibaba.fastjson.JSONObject;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Set;

/**
 * @Auther: dengyong
 * @Date: 2021/04/01/18:07
 * @Description:
 */
public class Demo {

  public static void main(String[] args) {
//    test();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("a","aa");
    jsonObject.put("b","bb");

    Set<String> keySet = jsonObject.keySet();

    for (String key : keySet){
      System.out.println(key+"----"+jsonObject.getString(key));
    }


  }

  private static void test() {
    Socket socket = new Socket();
    SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8001);
    try {
      socket.connect(socketAddress, 3000);
      System.out.println("ok");
    } catch (Exception e) {
      System.out.println("faild");
    } finally {
      try {
        socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


}
