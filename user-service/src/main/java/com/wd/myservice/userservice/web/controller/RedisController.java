package com.wd.myservice.userservice.web.controller;


import com.wd.myservice.userservice.web.myinterface.UrlLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "redis")
@Api(tags = "用户操作模块API")//作用在模块API类上，对API模块进行说明
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @UrlLog
    @ApiOperation("添加用户接口")//作用在API方法上，对操作进行说明
    @GetMapping("add/key/{key}/value/{value}")
    public Object add(@PathVariable(value = "key") String key, @PathVariable(value = "value") Integer value) {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key, value);
        return ops.get(key);
    }

    @UrlLog
    @ApiOperation("删除key ")
    @GetMapping("del/key/{key}")
    public Object del(@PathVariable(value = "key") String key) {
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }
    @UrlLog
    @ApiOperation("key ")
    @GetMapping("get/key/{key}")
    public Object get(@PathVariable(value = "key") String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o;
    }

}
