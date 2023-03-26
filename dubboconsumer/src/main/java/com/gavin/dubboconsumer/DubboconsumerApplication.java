package com.gavin.dubboconsumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboconsumerApplication.class, args);
    }

}
