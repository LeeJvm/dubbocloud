package com.gavin.test;

import com.gavin.pojo.SentimentDTO;

import java.util.ArrayList;

/**
 * Created by 17428 on 2023/3/21.
 */


/*1，自定义注解
2，自定义注解实现aop，完善日志功能
3，自定义注解实现全局异常处理*/


public class Main {


    public static void main(String[] args) {

        SentimentDTO sentimentDTO = new SentimentDTO();
        sentimentDTO.setEndDate("20220201");
        System.out.println(sentimentDTO);

    }


}
