package com.wd.myservice.userservice.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyConfigRedisTemplateTest {

    @Autowired
    private  RedisTemplate redisTemplate;


    /**
     * redis分布式锁测试
     */
    @Test
    public void test4() throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(1000);
        ArrayList<Runnable> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            new MyThread().start();
        }

    }

    @Data
    @NoArgsConstructor
    class MyThread extends Thread {
        private Integer socket;

        //在MyRedisConfig文件中配置了redisTemplate的序列化之后， 客户端也能正确显示键值对了
        @Override
        public void run() {
            socket = (Integer) redisTemplate.opsForValue().get("socket");
            synchronized (this) {
                if (socket > 0) {
                    socket--;
                    System.out.println("当前线程" + Thread.currentThread().getName() + "当前库存" + socket);
                    redisTemplate.opsForValue().set("socket", socket);
                } else {
                    System.out.println("当前线程" + Thread.currentThread().getName() + "当前库存" + socket);
                }
            }
        }
    }
}