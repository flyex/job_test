package com.flyex.dataWarehouse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static boolean validateLog(String log){

        try {
            JSONObject jsonObject = JSONObject.parseObject(log);

            JSONObject baseinfo = jsonObject.getJSONObject("baseinfo");
            JSONArray extrainfo = jsonObject.getJSONArray("extrainfo");

            if (!(baseinfo.getString("name").isEmpty()
                    |baseinfo.getString("age").isEmpty()
                    |baseinfo.getString("sex").isEmpty()
                    |baseinfo.getString("address").isEmpty()
                    |extrainfo.size()<2)){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            logger.error("parse error,message is: " + log);
            logger.error(e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) {
        String line = "{\"baseinfo\":{\"name\":\"小李\",\"age\":\"18\",\"sex\":\"man\",\"address\":\"天潼路\"},\"extrainfo\":[{\"interests\":\"swimming\",\"all\":\"游泳10m\"},{\"interests\":\"running\",\"all\":\"跑100m\"}]}";
        System.out.println(validateLog(line));
    }

}
