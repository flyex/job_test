package com.flyex.pageTopn;

import org.apache.hadoop.conf.Configuration;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("topn.xml");
        System.out.println(conf.get("top_n"));
    }
}
