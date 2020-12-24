package com;

import com.flyex.utils.LogUtils;

public class Test {

    public static void main(String[] args) {

        String line = "13:55:10.187 [main] INFO  com.flyex.AppMain - 1606974910187|{\"cm\":{\"ln\":\"-48.8\",\"sv\":\"V2.5.3\",\"os\":\"8.1.7\",\"g\":\"65T94DWJ@gmail.com\",\"mid\":\"m341\",\"nw\":\"4G\",\"l\":\"en\",\"vc\":\"10\",\"hw\":\"640*1136\",\"ar\":\"MX\",\"uid\":\"u801\",\"t\":\"1606970784457\",\"la\":\"22.0\",\"md\":\"sumsung-12\",\"vn\":\"1.3.3\",\"ba\":\"Sumsung\",\"sr\":\"S\"},\"ap\":\"com.flyex.AppMain\",\"et\":[{\"ett\":\"1606907365076\",\"en\":\"start\",\"kv\":{\"entry\":\"4\",\"loading_time\":\"2\",\"action\":\"1\",\"open_ad_type\":\"2\",\"detail\":\"\"}}]}\n" ;

        String s  = line.split("AppMain - ")[1];

//        String number = s.split("\\|")[0];
//        String json = s.split("\\|")[1];



        System.out.println(s.contains("apple"));





    }
}
