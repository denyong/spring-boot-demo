package com.niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dengyong
 * @description
 * @create 2022/3/2 18:54
 */
public class Test5 {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        countDownLatch.await(1, TimeUnit.SECONDS);

        for (String s: list){
            System.out.println(s);
        }
    }
}
