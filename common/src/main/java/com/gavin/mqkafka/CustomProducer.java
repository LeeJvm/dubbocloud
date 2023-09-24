package com.gavin.mqkafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Properties;

/**
 * Created by 17428 on 2023/7/23.
 */




public class CustomProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        // 配置可以使用具体的字符串，也可以使用producerConfig中配置的静态变量名
        // 设置集群配置
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.233.129:19092");
        // ack机制
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        // 批次大小:消息大小为16384才发送消息
        props.put("batch.size", 16384);
        // 等待时间:如果消息大小迟迟不为batch.size大小，则等待linger.ms时间后直接发送
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // ReadAccumulator缓冲区大小
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 构造producer
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        // 生产消息
        for (int i = 1; i <= 10; i++) {
            // 构造消息体
            producer.send(new ProducerRecord<>("test", "test-" + i, "test-" + i));
        }
        producer.close();
    }





}

