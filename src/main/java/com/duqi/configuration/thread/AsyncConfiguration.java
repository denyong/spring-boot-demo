package com.duqi.configuration.thread;

import com.alibaba.fastjson.JSONObject;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author dengyong
 * @Auther: dengyong
 * @Date: 2021/02/02/9:57
 * @Description:
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {

  @Bean("executor")
  public Executor doSomethingExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //核心线程数（获取硬件）：线程池创建时候初始化的线程数
    int corePoolSize = Runtime.getRuntime().availableProcessors();
    // 核心线程数：线程池创建时候初始化的线程数
    executor.setCorePoolSize(corePoolSize);
    // 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
    executor.setMaxPoolSize(corePoolSize + 5);
    // 缓冲队列：用来缓冲执行任务的队列
    executor.setQueueCapacity(500);
    // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
    executor.setKeepAliveSeconds(60);
    // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
    executor.setThreadNamePrefix("dy-thread-");
    // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是主线程）
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    // 线程池初始化
    executor.initialize();
    return executor;
  }
}
