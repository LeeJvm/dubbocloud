package com.gavin.consumer.framework.interceptor;

import com.gavin.enums.ProcessCodeEnum;
import com.gavin.pojo.ResponseResult;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by 17428 on 2023/9/24.
 */


@Slf4j
@Component
@Aspect
public class ExceptionAspect {


    @Pointcut("execution(* com.gavin.consumer.rest..*(..))") //切入点是: rest目录以及其子目录下的所有的controller
    public void exceptionPointCut() {

    }


    @Around("exceptionPointCut()")
    public ResponseResult handleControllerMethod(ProceedingJoinPoint pjp) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ResponseResult r =null;
        try {
            log.info("执行Controller开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
            Object result =  pjp.proceed(pjp.getArgs());
            log.info("执行结果：{}"+result);
            log.info("执行Controller结束: " + pjp.getSignature() + "， 返回值：" + result.toString());
            log.info("耗时：" + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "(毫秒).");
            r = new ResponseResult(ProcessCodeEnum.SUCCESS, result);
        } catch (Throwable throwable) {
            Object errMsg = handlerException(pjp, throwable);
            r = new ResponseResult(ProcessCodeEnum.UNKNOWN, errMsg);
        }
        return r;

    }


    /**
     * 处理异常信息
     */
    private Object handlerException(ProceedingJoinPoint pjp, Throwable e) {
        Object errMsg = null;
        if (e instanceof AuthenticationException) {
            AuthenticationException authenticationException = (AuthenticationException) e;
            log.error("AuthenticationException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + authenticationException.getMessage() + "}", e);
            errMsg = ProcessCodeEnum.LOGIN_FAIL.getMessage();
        } else if (e instanceof RuntimeException) {
            log.error("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
            errMsg = ProcessCodeEnum.UNKNOWN.getMessage();
        } else {
            log.info("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);

            errMsg = ProcessCodeEnum.UNKNOWN.getMessage();
        }
        return errMsg;
    }






}
