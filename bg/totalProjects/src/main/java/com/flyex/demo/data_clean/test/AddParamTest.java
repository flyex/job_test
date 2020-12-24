package com.flyex.demo.data_clean.test;

import org.apache.hadoop.conf.Configuration;

public class AddParamTest {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        //conf.addResource("aa.properties");
        conf.set("aa","小明");

        String name = conf.get("bb","小李");

        System.out.println(name);
    }
}
