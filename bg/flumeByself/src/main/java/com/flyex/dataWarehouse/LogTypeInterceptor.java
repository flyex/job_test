package com.flyex.dataWarehouse;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class LogTypeInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //获取flume接收消息头，添加额外信息
        Map<String, String> headers = event.getHeaders();

        //获取消息体并分类
        byte[] eventBody = event.getBody();
        String json = new String(eventBody, Charset.forName("UTF-8"));
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject baseinfo = jsonObject.getJSONObject("baseinfo");

        String log_type = "";
        if (baseinfo.getString("sex").equals("man")){
            log_type = "man";
        }else {
            log_type = "woman";
        }

        headers.put("logType",log_type);

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list){
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
