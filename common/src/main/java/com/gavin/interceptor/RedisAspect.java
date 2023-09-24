package com.gavin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 *  通过监控调用redis api，捕获异常，然后进行记录和通知
 *  aop环绕通捕获异常
 *  可以实现：redis处理异常后的逻辑补充，也就是说连接redis异常后可以连接数据库进行业务处理
 *
 */


@Aspect
@Component
@Slf4j


public class RedisAspect {

    @Pointcut("execution(* com.gavin.redis.*.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object handleException(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            result= joinPoint.proceed();
        } catch (Throwable throwable) {
            //日志写入日志文件
            log.error("redis may be some wrong");
            //日志也可以写入数据库表，针对记录重要日志

        }
        return result;
    }


}
