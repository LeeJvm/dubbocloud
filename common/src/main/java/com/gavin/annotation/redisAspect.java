package com.gavin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 17428 on 2023/4/8.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface redisAspect {
    //可以在使用注解时候标记是哪个地方的：redis就记录redis
    //off course可以通过枚举进行罗列
    String value() default "" ;

}
