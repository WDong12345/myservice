package com.wd.myservice.userservice.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Configuration
@Component
public class MyThreadPool {

    private static ThreadPoolTaskExecutor threadPool;

    public ThreadPoolTaskExecutor getPool() {
        return threadPool;
    }

    public MyThreadPool() {
        this.threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(30);
        threadPool.setAwaitTerminationMillis(3000);
        threadPool.setMaxPoolSize(100);
        threadPool.setKeepAliveSeconds(300);
        threadPool.setThreadNamePrefix("减库存线程——");
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.initialize();
    }

    public static ThreadPoolTaskExecutor getThreadPool() {
        return threadPool;
    }

    public static void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        MyThreadPool.threadPool = threadPool;
    }

    public static void main(String[] args) throws IOException {
        StringBuffer adsdf = new StringBuffer("adsdf");
        adsdf.append("faaaa");
        StringBuilder builder = new StringBuilder("sfadfsf");
        String s = String.valueOf(builder);
        System.out.println(s );
        Runtime runtime = Runtime.getRuntime();
        System.out.println("最大内存(M):" + runtime.maxMemory() / 1024 / 1024);
        System.out.println("总内存(M):" + runtime.totalMemory() / 1024 / 1024);
        System.out.println("可用内存(M):" + runtime.freeMemory() / 1024 / 1024);
//        System.out.println("打开计算器:" + runtime.exec("calc"));
        System.out.println("jvm可用的处理器核心的数 量:" + runtime.availableProcessors() / 1024 / 1024);
        //通知JVM进行gc aaaaaaaaaaaaaaaa
        runtime.gc();
        System.gc();
    }

}
