package com.duqi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author dengyong
 * @description
 * @create 2022/3/6 14:05
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedissonClient redissonClient;
    
    private final Redisson redisson;

    @GetMapping("/testRedis")
    public String testRedis() throws Exception{
        RLock dengyong = redisson.getLock("dengyong");
        dengyong.lock(8, TimeUnit.SECONDS);
        RReadWriteLock dy = redisson.getReadWriteLock("dy");
        dy.readLock().lock();
        dy.writeLock().lock();
        dy.readLock().tryLock(100,10,TimeUnit.SECONDS);
        dy.writeLock().tryLock(100,10,TimeUnit.SECONDS);

        try{
            System.out.println("加锁成功：线程id"+Thread.currentThread().getId());
            Thread.sleep(30000);
        }catch (Exception e){

        }finally {
            dengyong.unlock();
            System.out.println("释放锁成功：线程id"+Thread.currentThread().getId());
        }
        return "ok";
    }

    @GetMapping("/park")
    public String park() throws Exception{
        RSemaphore park = redisson.getSemaphore("car");
        park.acquire();
        return "ok";
    }

    @GetMapping("/leave")
    public String leave() throws Exception{
        RSemaphore park = redisson.getSemaphore("car");
        park.release();
        return "ok";
    }
}
