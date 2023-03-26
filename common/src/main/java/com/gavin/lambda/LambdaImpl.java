package com.gavin.lambda;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by 17428 on 2023/2/23.
 */
public class LambdaImpl {

    public static void main(String[] args) {

        LambdaI lambdaI = () -> System.out.println("ww");
        lambdaI.method1();


        PrintStream out = System.out;
        Consumer<String> tConsumer = out::print;
        tConsumer.accept("dd");


        Function<Integer,String > function;
    }


}
