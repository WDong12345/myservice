package com.wd.myservice.userservice.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 此配置可以在redis中存储对象，序列化后会存储对象全路径，取对象时可强转为之前对象，自动根据反射创建一个对象。
 * 序列化后的数据如下：
 * {
 *   "@class": "com.wd.myservice.userservice.web.entity.User",    //对象全路径
 *   "id": null,
 *   "username": "adsadfwewdsad",
 *   "password": "24121asd",
 *   "city": "重庆",
 *   "userType": null,
 *   "district": null,
 *   "street": null,
 *   "address": null
 * }
 */
@Configuration
public class MyRedisConfig {
 
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
 
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        ////参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        return redisTemplate;
    }
 
    private RedisSerializer<String> keySerializer(){
        return new StringRedisSerializer();
    }
 
    //使用Jackson序列化器
    private RedisSerializer<Object> valueSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }
}