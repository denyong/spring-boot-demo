package com.duqi.algorithm.thread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: dengyong
 * @Date: 2021/02/25/9:11
 * @Description: 多线程按顺序执行的几种方法
 */
public class ThreadDemo {

  public static void main(String[] args) throws InterruptedException {
    // 方法一：通过join()方法使当前线程阻塞，等待指定的线程执行完毕之后，继续执行。
    // 过程：当thread2执行遇到thread1.join()时，会先去执行thread1的内容，执行完毕再去执行thread2，这样就保证了顺序
    testThread();
    // 方法二：在主线程中通过join()来执行顺序
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("1.打开冰箱门");
      }
    });

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("2.取出大象");
      }
    });

    Thread thread3 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("3.关闭冰箱门");
      }
    });

    thread1.start();
    // 当主线程执行到这里，就会先执行thread1,这样就保证了顺序
    thread1.join();
    thread2.start();
    // 当主线程执行到这里，就会先执行thread2,这样就保证了顺序
    thread2.join();
    thread3.start();

    // 方法三：通过倒数计时器CountDownLatch实现
    testThread2();
  }

  private static void testThread2() {
    CountDownLatch countDownLatch1 = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(1);
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("1.打开冰箱门");
        countDownLatch1.countDown();
      }
    });

    Thread thread2 = new Thread(new Runnable() {

      @Override
      public void run() {
        try{
          countDownLatch1.await();
          System.out.println("2.取出大象");
          countDownLatch2.countDown();
        }catch (InterruptedException e){
          System.out.println(e);
        }
      }
    });

    Thread thread3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try{
          countDownLatch2.await();
          System.out.println("3.关闭冰箱门");
        }catch (InterruptedException e){
          System.out.println(e);
        }
      }
    });

    thread1.start();
    thread2.start();
    thread3.start();


    // 方法四：使用线程池newSingleThreadExecutor
    testThread3();
  }

  private static void testThread3() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("1.打开冰箱门");
      }
    });

    Thread thread2 = new Thread(new Runnable() {

      @Override
      public void run() {
        System.out.println("2.取出大象");
      }
    });

    Thread thread3 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("3.关闭冰箱门");
      }
    });
    executorService.submit(thread1);
    executorService.submit(thread2);
    executorService.submit(thread3);
    executorService.shutdown();
  }

  private static void testThread() {
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("1.打开冰箱门");
      }
    });

    Thread thread2 = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          thread1.join();
        } catch (InterruptedException e) {
          System.out.println(e);
        }
        System.out.println("2.取出大象");
      }
    });

    Thread thread3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          thread2.join();
        } catch (InterruptedException e) {
          System.out.println(e);
        }
        System.out.println("3.关闭冰箱门");
      }
    });

    thread1.start();
    thread2.start();
    thread3.start();
  }
}


