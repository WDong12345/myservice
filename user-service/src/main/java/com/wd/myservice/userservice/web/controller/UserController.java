package com.wd.myservice.userservice.web.controller;


import com.wd.myservice.userservice.web.entity.User;
import com.wd.myservice.userservice.web.myinterface.UrlLog;
import com.wd.myservice.userservice.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jetbrick.util.codec.MD5Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "user")
@Api(tags = "用户操作模块API")//作用在模块API类上，对API模块进行说明
public class UserController {
    @Autowired
    private UserService userService;

    @UrlLog
    @ApiOperation("添加用户接口")//作用在API方法上，对操作进行说明
    @GetMapping("test")
    public String login( ) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            Runnable runnable = new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    String test = userService.test();
                    builder.append(test);
                }
            };
            runnable.run();
        }
        return builder.toString();
    }


    @UrlLog
    @ApiOperation("添加用户接口")//作用在API方法上，对操作进行说明
    @GetMapping("login/username/{username}/password/{password}")
    public Object login(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {
        byte[] bytes = MD5Utils.md5( password.getBytes(StandardCharsets.UTF_8));
        String s = String.valueOf(bytes);
        User one = userService.getOne(username, s);
        if (one == null) {
            return "用户名或密码错误";
        }
        return one;
    }

    @UrlLog
    @ApiOperation("添加用户接口")//作用在API方法上，对操作进行说明
    @GetMapping("register/username/{username}/password/{password}")
    public Object register(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {
        byte[] bytes =
                MD5Utils.md5(password.getBytes(StandardCharsets.UTF_8));
        String s = String.valueOf(bytes);
        User one = userService.register(username,s);
        if (one == null) {
            return "用户名或密码错误";
        }
        return one;
    }
}
