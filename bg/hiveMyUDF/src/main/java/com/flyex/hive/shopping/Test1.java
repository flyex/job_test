package com.flyex.hive.shopping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Test1 {

    public static void main(String[] args) {

        String line = "14:39:43.358 [main] INFO  com.flyex.AppMain - 1607323183358|{\"cm\":{\"ln\":\"-113.4\",\"sv\":\"V2.3.0\",\"os\":\"8.1.7\",\"g\":\"7BZE66H5@gmail.com\",\"mid\":\"m305\",\"nw\":\"WIFI\",\"l\":\"pt\",\"vc\":\"0\",\"hw\":\"1080*1920\",\"ar\":\"MX\",\"uid\":\"u643\",\"t\":\"1607240453416\",\"la\":\"11.4\",\"md\":\"sumsung-19\",\"vn\":\"1.3.4\",\"ba\":\"Sumsung\",\"sr\":\"Z\"},\"ap\":\"com.flyex.AppMain\",\"et\":[{\"ett\":\"1607273557535\",\"en\":\"start\",\"kv\":{\"entry\":\"2\",\"loading_time\":\"12\",\"action\":\"1\",\"open_ad_type\":\"2\",\"detail\":\"\"}}]}";

        String[] split = line.split("AppMain - ");

        String jsonStr = split[1];
        String json = jsonStr.substring(14);
        String st = jsonStr.substring(0,13);

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject cm = jsonObject.getJSONObject("cm");
        JSONArray et = jsonObject.getJSONArray("et");

        System.out.println(st);


    }
}
