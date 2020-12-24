package com.flyex.hive;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONException;

import java.util.ArrayList;

public class BaseInfoUDF extends UDF {

    public static String evaluate(String line,int index){

        JSONObject jsonObject = JSONObject.parseObject(line);
        ArrayList<String> res = new ArrayList<>();

        JSONObject baseinfo = jsonObject.getJSONObject("baseinfo");
        JSONArray jsonArray = jsonObject.getJSONArray("extrainfo");

        res.add(0,baseinfo.getString("name"));
        res.add(1,baseinfo.getString("age"));
        res.add(2,baseinfo.getString("sex"));
        res.add(3,baseinfo.getString("address"));
        res.add(4,jsonArray.toJSONString());

        return res.get(index);
    }

    public static void main(String[] args) {
        String line = "{\"baseinfo\":{\"name\":\"小李\",\"age\":\"18\",\"sex\":\"man\",\"address\":\"天潼路\"},\"extrainfo\":[{\"interests\":\"swimming\",\"all\":\"游泳10m\"},{\"interests\":\"running\",\"all\":\"跑100m\"}]}";
        String json = evaluate(line,4);

        JSONArray jsonArray = JSONObject.parseArray(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i).toJSONString());
        }
    }
}

