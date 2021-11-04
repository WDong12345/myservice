package com.wd.myservice.userservice.web.myinterface;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class UrlLogHandle {

    /**
     * 通过注解对所有注解的请求添加请求用时
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation( com.wd.myservice.userservice.web.myinterface.UrlLog)")
    public Object addLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        long end = System.currentTimeMillis();
        log.info("当前方法：{},运行时间: {} 毫秒", method, (end - start));
        return pjp.proceed();
    }

}
