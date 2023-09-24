package com.gavin.util;

import com.alibaba.fastjson.JSON;
import com.gavin.pojo.ResponseResult;
import com.gavin.pojo.SentimentDTO;
import org.springframework.amqp.utils.SerializationUtils;

/**
 * Created by 17428 on 2023/8/13.
 */
public class SerializaUtil {

    /**
     * 1，序列化 Key
     * 2，存储value：如果value是对象，将对象转换为string json，然后再序列化
     *
     * @param key
     * @return
     */
    public static byte[]  serialzeKey(String key) {
        if ("".equals(key) || key.trim().length() == 0) {
            return null;
        }
        byte[]  keyArray = SerializationUtils.serialize(key);
        return keyArray;
    }


    /**
     * 反序列化key
     * @param key
     * @return
     */
    public static String deserializeKey(byte[] key) {
        if (key.length==0) {
            return null;
        }
        Object deserialize = SerializationUtils.deserialize(key);
        return String.valueOf(deserialize);
    }

    /**
     * 序列化value     ------leaving
     * @param value
     * @return
     */
    public static byte[] toJSONArray(Object value) {
       // JSON.toJSONString()

        return null;
    }


    /**
     * 反序列化value  ------leaving
     * @param  JSONArray
     */
    public static Object praseJSONArray(byte[]  JSONArray) {


        return null;
    }




    public static void main(String[] args) {

/*
        byte[] hellos = serialzeKey("你好");
        for (int i = 0; i < hellos.length; i++) {
            System.out.println(hellos[i]);
        }

        //反序列化方法：
        Object deserialize = SerializationUtils.deserialize(hellos);

        System.out.println(deserialize);
*/

        SentimentDTO sentimentDTO = new SentimentDTO("222", "4444", null);

        byte[] serialize = SerializationUtils.serialize(sentimentDTO);
        System.out.println(serialize);
        Object deserialize = SerializationUtils.deserialize(serialize);
        System.out.println(deserialize);




    }



}
