package com.flyex.flinkDemo;


import org.apache.flink.api.java.tuple.Tuple1;

public class Test {
    public static void main(String[] args) {

        Tuple1<String> stringTuple1 = new Tuple1<String>("asd");

        String f0 = stringTuple1.f0;

        System.out.println(f0);

    }
}
