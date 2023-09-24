/*
package com.gavin.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Log
public class RabbitMqConfig {

    @Value("rabbitmq.host")
    private String host;

    @Value("rabbitmq.port")
    private int port;

    @Value("rabbitmq.virtual")
    private String virtual;

    @Value("rabbitmq.username")
    private String username;

    @Value("rabbitmq.password")
    private String password;


    */
/**
     * 将配置项抽取到nacos，注意static的属性注入
     * @return
     * @throws Exception
     *//*

    @Bean
    public  Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(host);
        //端口
        factory.setPort(port);
        //设置账号信息，用户名、密码、vhost
       // factory.setVirtualHost(virtual);//设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        factory.setUsername(username);
        factory.setPassword(password);
        // 通过工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }






}
*/
