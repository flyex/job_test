package com.flyex.myhive;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hive.ql.exec.UDF;

public class BaseInfoUDF extends UDF {

    public String evaluate(String line,String key){

        JSONObject jsonObject = JSONObject.parseObject(line);

        JSONObject baseinfo = jsonObject.getJSONObject("baseinfo");

        String res = baseinfo.getString(key);

        return res;
    }

}
