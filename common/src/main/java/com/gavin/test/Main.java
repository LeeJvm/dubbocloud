package com.gavin.test;

import com.gavin.enums.ProcessCodeEnum;
import com.gavin.pojo.ResponseResult;
import com.gavin.pojo.SentimentDTO;
import com.gavin.redis.JedisTool;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;

/**
 * Created by 17428 on 2023/3/21.
 */


/*1，自定义注解
2，自定义注解实现aop，完善日志功能
3，自定义注解实现全局异常处理*/


@RestController
public class Main {


    public static void main(String[] args) {

        SentimentDTO sentimentDTO = new SentimentDTO();
        sentimentDTO.setEndDate("20220201");
        System.out.println(sentimentDTO);


    }

    @Autowired
    JedisPool jedisPool;

    @Autowired
    JedisTool jedisTool;

    @RequestMapping(value = "/redisTest", method = RequestMethod.GET)
    public boolean getKeys(String key) {
        String s = jedisPool.getResource().get(key);
        String key1 = jedisTool.getKey(key);

        new ResponseResult<SentimentDTO>(ProcessCodeEnum.SUCCESS, new SentimentDTO());

        return s==key1;
    }



}





