/*
package com.gavin.mq;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
public class Recv2PublishSub {

    private final static String QUEUE_NAME = "fanout_exchange_queue_email";//邮件队列

    private final static String EXCHANGE_NAME = "test_fanout_exchange";

    @Autowired
    Connection connection;



    public String RabbitmqRecv(String queueName,String exchangeName) throws  Exception {
        String msg = null;
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queueName, false, false, false, null);
        //绑定交换机
        channel.queueBind(QUEUE_NAME, exchangeName, "");
        //消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){ //方法内部类，类别局部变量
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
             String   msg = new String(body);
            }
        };

        // 监听队列，自动返回完成
        channel.basicConsume(QUEUE_NAME, true, consumer);

        return msg;
    }





}
*/
