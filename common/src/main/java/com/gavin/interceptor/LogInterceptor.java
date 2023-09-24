package com.gavin.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 整个类是由切点（pointcut）和四种通知advice(增强)组成
 * 也就是说这个类就是切面（aspect）=切点（pointcut）+增强
 *
 */

//拦截器相关注解
@Aspect
@Component
//日志通用注解
@Slf4j



public class LogInterceptor {

    /**
     *
     * 声明pointcut（切入点）：切入点是通过注解切入的，需要添加自定义注解作为参数；是一个空方法
     * 其实就是抽象出来一个切点，给下面的通知使用：@Before("annotationPointCut()")
     */
    @Pointcut("@annotation(com.gavin.annotation.logAspect)")
    public void annotationPointCut() {

    }


    /**
     * 前置通知
     *  拦截指定注解的方法
     *  获取参数也是通过aspectj的相关api
     */
    @Before("annotationPointCut()")
    public void before(JoinPoint jp) {
       //将方法名作为参数传进来
        Signature signature = jp.getSignature();
        String name = signature.getName();

        log.info("方法开始了");
    }

    /**
     *  后置通知：方法执行后执行此通知
     */
    @After("annotationPointCut()")
    public void after() {
        log.info("方法结束了");

    }

    /**
     * 环绕通知：
     * 类似动态代理的整个过程；
     * 可以在任意时候执行目标方法，可以在任意地方植入aop代码；
     * 因此环绕通知包含了前置后置等通知的功能
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint pjp ) throws Throwable {

        Object proceed = pjp.proceed(); //执行拦截到的方法

        return proceed;
    }




}
