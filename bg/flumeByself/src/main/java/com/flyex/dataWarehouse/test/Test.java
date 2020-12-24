package com.flyex.dataWarehouse.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

    public static void main(String[] args) {

        String line = " {\"baseinfo\":{\"name\":\"小李\",\"age\":\"18\",\"sex\":\"man\",\"address\":\"天潼路\"},\"extrainfo\":[{\"interests\":\"swimming\",\"all\":\"游泳10m\"},{\"interests\":\"running\",\"all\":\"跑100m\"}]}" ;
        String line2 = " {\"baseinfo\":{\"name\":\"小李\",\"age\":\"18\",\"sex\":\"man\",\"address\":\"天潼路\"},\"extrainfo\":[]} " ;

        JSONObject jsonObject = JSONObject.parseObject(line);
        JSONObject jsonObject2 = JSONObject.parseObject(line2);

        JSONObject baseinfo = jsonObject.getJSONObject("baseinfo");
        JSONArray extrainfo = jsonObject.getJSONArray("extrainfo");
        System.out.println(baseinfo.getString("sex").equals("man"));

        JSONArray extrainfo2 = jsonObject2.getJSONArray("extrainfo");
       // System.out.println(extrainfo.size());


    }
}
