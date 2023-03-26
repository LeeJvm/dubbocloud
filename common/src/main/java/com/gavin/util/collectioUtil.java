package com.gavin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 17428 on 23-2-5.
 */
public class collectioUtil {


    public static void main(String[] args) throws Exception {

      /*  ArrayList<String> strings = new ArrayList<>();
        strings.add("D");
        strings.add("f");

        strings.stream().map(String::toLowerCase);
*/
  /*      int i = 0;
        String s = testMethod(i);
        System.out.println(s);*/

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(1);
        jsonArray.add("");
        System.out.println(jsonArray);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1, 1);
        objectObjectHashMap.put(null, 2);
        String s1 = JSON.toJSONString(objectObjectHashMap, SerializerFeature.WriteMapNullValue);
        System.out.println(s1);


    }


    private static String testMethod(Integer i) throws Exception {

        if (i == 0) {
            throw new Exception("抛出异常了");
        }
        i++;

        return i+"d";


    }



}
