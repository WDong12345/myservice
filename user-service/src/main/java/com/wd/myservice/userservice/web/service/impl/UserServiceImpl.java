package com.wd.myservice.userservice.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wd.myservice.userservice.util.MyThreadPool;
import com.wd.myservice.userservice.web.dao.UserDao;
import com.wd.myservice.userservice.web.entity.User;
import com.wd.myservice.userservice.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private MyThreadPool pool;
    @Autowired
    private RedisTemplate redisTemplate;

    public synchronized String test() throws InterruptedException {
        ValueOperations ops = redisTemplate.opsForValue();

        Boolean aBoolean = getBooleans(ops);

        final Integer[] socket = {(Integer) redisTemplate.opsForValue().get("socket")};

        Callable callable = new Callable<String>() {
            @Override
            public String call() {
                //使用redisTemplate  封装的  Redis命令：DECR命令 可实现对应key的value 减1
                //底层用lua脚本执行，可保证原子性
                ops.decrement("socket");
                redisTemplate.delete("key");
                return "当前线程" + Thread.currentThread().getName() + "当前库存" + socket[0] + "\r\n";
            }
        };
        Future submit;
        while (true) {
            if (aBoolean) {
                  submit =   pool.getPool().submit(callable);
                  break;
            }else {
                Thread.sleep((int)(Math.random()*100));
                aBoolean = getBooleans(ops);
                System.out.println("当前线程没有拿到锁" + Thread.currentThread().getName() + "当前库存" + socket[0]  );
            }
        }

        String o = null;
        try {
            o = (String) submit.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return o;

    }

    private Boolean getBooleans(ValueOperations ops) {
        Boolean aBoolean = ops.setIfAbsent("key", "aa", 5, TimeUnit.SECONDS);
        return aBoolean;
    }

    @Override
    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int insert = baseMapper.insert(user);
        User one = this.getOne(username, password);
        return one;
    }

    @Override
    public User getOne(String username, String password) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        User one = baseMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getUsername, username)
                        .eq(User::getPassword, password)
        );
        return one;
    }

}

