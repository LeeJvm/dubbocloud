package com.gavin.stream;

import java.util.ArrayList;

/**
 * Created by 17428 on 2023/2/23.
 */
public class StreamTest   {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("dd");
        strings.add("张三");
        strings.add("张飞");
        strings.add("张三丰");
        strings.add("张三");
        strings.add("李四");
        strings.add("孙悟空");
        strings.add("张一飞");
        ArrayList<String> strings2 = new ArrayList<>();

        strings.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(s -> strings2.add(s));
        strings2.stream().parallel().forEach(s -> System.out.println(s));



    }



}
