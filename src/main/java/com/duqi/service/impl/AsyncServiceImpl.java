package com.duqi.service.impl;

import java.util.concurrent.CompletableFuture;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @Auther: dengyong
 * @Date: 2021/02/02/10:49
 * @Description:
 */
@Service
@Slf4j
public class AsyncServiceImpl {
  @Async("executor")
  public CompletableFuture<String> doSomething1(String message) throws InterruptedException {
    log.info("do something1: {}", message);
    Thread.sleep(7000);
    return CompletableFuture.completedFuture("do something1: " + message);
  }

  @Async("executor")
  public CompletableFuture<String> doSomething2(String message) throws InterruptedException {
    log.info("do something2: {}", message);
    Thread.sleep(8000);
    return CompletableFuture.completedFuture("; do something2: " + message);
  }

  @Async("executor")
  public CompletableFuture<String> doSomething3(String message) throws InterruptedException {
    log.info("do something3: {}", message);
    Thread.sleep(9000);
    return CompletableFuture.completedFuture("; do something3: " + message);
  }
}
