package com.duqi;

/**
 * @Auther: dengyong
 * @Date: 2021/01/29/9:21
 * @Description:
 */
public class ModelDemo {

}

/**
 * 单例模式-懒汉式 懒汉式故名思意就是懒，是等到需要使用的时候再创建单例。 这就需要将构造函数私有化，在外部就不能创建对象，由内部创建。
 */
class SingleDemo {

  // 先声明  不创建对象
  private static SingleDemo singleDemo = null;

  // 构造器必须私有
  private SingleDemo() {
  }

  // 提供公有静态方法返回对象
  public static SingleDemo getInstance() {
    if (singleDemo == null) {
      singleDemo = new SingleDemo();
    }
    return singleDemo;
  }

}

/**
 * 单例模式-懒汉式 双检锁模式
 */
class SingleDemo2 {

  private static SingleDemo2 singleDemo2 = new SingleDemo2();

  private SingleDemo2() {
  }

  public static SingleDemo2 getInstance() {
    return singleDemo2;
  }
}

/**
 *
 */
class SingleDemo3 {

  private static SingleDemo3 singleDemo3 = null;

  private SingleDemo3() {
  }

  public static SingleDemo3 getInstance() {
    if (singleDemo3 == null) {
      synchronized (SingleDemo.class) {
        if (singleDemo3 == null) {
          singleDemo3 = new SingleDemo3();
        }
      }
    }
    return singleDemo3;
  }

}

class SingleDemo4{
  private SingleDemo4(){}
  private static class SingleDemo6{
    private static final SingleDemo4 singleDemo4 = new SingleDemo4();
  }

  public static SingleDemo4 getInstance(){
    return SingleDemo6.singleDemo4;
  }
}
