package com.gavin;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.gavin.consumer.mapper")
@EnableGlobalMethodSecurity(prePostEnabled =true) //开启springsecurity授权注解功能

public class DubboconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboconsumerApplication.class, args);
    }



}



