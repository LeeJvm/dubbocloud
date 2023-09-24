package com.gavin.mq;

/**
 * Created by 17428 on 2023/7/2.
 */


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@Configuration
public class DirectRabbitConfig {


    public static final String QUEUE_NAME = "TestDirectQueue"; //队列名称
    public static final String EXCHANGE_NAME = "TestDirectExchange"; //交换器名称
    public static final String ROUTING_KEY = "TestDirectRouting"; //路由键


    /**
     * 发送mq的对象，需要设置发送时候的相关属性，和回调确认逻辑
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //确认消息送到交换机(Exchange)回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String cause)
                {
                    //根据实际需要写入到日志文件或数据库
                    System.out.println("\n确认消息送到交换机(Exchange)结果：");
                    System.out.println("相关数据：" + correlationData);
                    System.out.println("是否成功：" + ack);
                    System.out.println("错误原因：" + cause);
                }
            });


        //确认消息送到队列(Queue)回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback(){
                @Override
                public void returnedMessage(ReturnedMessage returnedMessage) {
                    //根据实际需要写入到日志文件或数据库
                    System.out.println("\n确认消息送到队列(Queue)结果：");
                    System.out.println("发生消息：" + returnedMessage.getMessage());
                    System.out.println("回应码：" + returnedMessage.getReplyCode());
                    System.out.println("回应信息：" + returnedMessage.getReplyText());
                    System.out.println("交换机：" + returnedMessage.getExchange());
                    System.out.println("路由键：" + returnedMessage.getRoutingKey());
                }
            });
        return rabbitTemplate;

    }



    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {

        /** 参数说明：
         * 1，String name：队列名称
         * 2，durable：是否持久化,true开启持久化
         * 3, exclusive: 是否排他，false不排他
         * 4，altoDelete：是否自动删除，false不自动删除,true如果没有生产者或消费者使用该队列则删除
         * 5，arguements：队列的其他参数
         */
        return new Queue(QUEUE_NAME,true,false,false,null);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {

        /** 参数说明：
         *  1，name：交换机名称
         *  2，druable：是否持久化
         *  3，是否自动删除：意义跟队列参数名称一致
         */
        return new DirectExchange(EXCHANGE_NAME,true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with(ROUTING_KEY);
    }


}