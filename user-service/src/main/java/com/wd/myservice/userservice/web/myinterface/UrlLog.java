package com.wd.myservice.userservice.web.myinterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
/**
 * 自定义注解  对请求添加用时日志
 */
public   @interface UrlLog {

}
