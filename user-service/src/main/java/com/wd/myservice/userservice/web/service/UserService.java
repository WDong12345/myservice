package com.wd.myservice.userservice.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wd.myservice.userservice.web.entity.User;


public interface UserService extends IService<User> {
    public String test() throws InterruptedException;
    User getOne(String username, String password);

    User register(String username, String password);
}
