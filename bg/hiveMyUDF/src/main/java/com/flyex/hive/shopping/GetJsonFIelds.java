package com.flyex.hive.shopping;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hive.ql.exec.UDF;

public class GetJsonFIelds extends UDF {

    public static String evaluate(String json,String field){

        JSONObject jsonObject = JSONObject.parseObject(json);

        JSONObject kv = jsonObject.getJSONObject("kv");

        String res = kv.getString(field);

        if (res != null){
            return res;
        }else {
            return "";
        }

    }

    public static void main(String[] args) {

        String line = " {\"ett\":\"1607303015266\",\"en\":\"newsdetail\",\"kv\":{\"entry\":\"3\",\"newsid\":\"n966\",\"news_staytime\":\"4\",\"loading_time\":\"40\",\"action\":\"2\",\"showtype\":\"2\",\"category\":\"41\",\"type1\":\"\"}}";

        System.out.println(evaluate(line,"newsidd"));


    }
}
