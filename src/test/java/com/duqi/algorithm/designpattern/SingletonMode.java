package com.duqi.algorithm.designpattern;

import org.junit.Test;

/**
 * @Auther: dengyong
 * @Date: 2021/02/20/9:14
 * @Description: 单例模式
 */
public class SingletonMode {

  public static void main(String[] args) {
    System.out.println(Demo.getUniqueInstance());
    System.out.println(Demo.getUniqueInstance());
    System.out.println(Demo.getUniqueInstance());
    System.out.println(Demo.getUniqueInstance());
  }

}

class Demo {

  // 双检锁实现对象单例(线程安全)
  private volatile static Demo demo;

  private Demo() {
  }

  public static Demo getUniqueInstance() {
    if (demo == null) {
      synchronized (Demo.class) {
        if (demo == null) {
          demo = new Demo();
        }
      }
    }
    return demo;
  }
}
