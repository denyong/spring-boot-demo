package com.duqi.controller;

import com.duqi.service.impl.AsyncServiceImpl;
import io.swagger.annotations.ApiOperation;
import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: dengyong
 * @Date: 2021/02/02/10:48
 * @Description:
 */
@RestController
public class AsyncController {

  @Autowired
  private AsyncServiceImpl asyncService;

  @SneakyThrows
  @ApiOperation("异步 有返回值")
  @GetMapping("/open/somethings")
  public String somethings() {
    CompletableFuture<String> createOrder = asyncService.doSomething1("create order");
    CompletableFuture<String> reduceAccount = asyncService.doSomething2("reduce account");
    CompletableFuture<String> saveLog = asyncService.doSomething3("save log");

    // 等待所有任务都执行完
    CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
    // 获取每个任务的返回结果
    String result = createOrder.get() + reduceAccount.get() + saveLog.get();
    return result;
  }
}
