package com.flyex.io;

public class Test2 {
    public static void main(String[] args) {

        String line = "http://bigdata.edu360.cn/laozhang";

        String[] fields = line.split("/");

//        for (String s:fields){
//            System.out.println(s);
//        }

        System.out.println(fields[2]+fields[3]);
    }
}
