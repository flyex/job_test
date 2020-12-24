package com.flyex.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.LinkedHashMap;
import java.util.List;

public class JsonInterceptor implements Interceptor {

    private String[] schema;
    private String separator;

    public JsonInterceptor(String schema,String separator){
        this.schema = schema.split(",");
        this.separator = separator;
    }

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        LinkedHashMap<String, String> tuple = new LinkedHashMap<>();
        String line = new String(event.getBody());
        String[] fields = line.split(separator);

        for (int i=0;i<schema.length;i++){
            String key  = schema[i];
            String value = fields[i];
            tuple.put(key,value);
        }
        String json = JSONObject.toJSONString(tuple);
        event.setBody(json.getBytes());
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event:events){
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        private String fields;
        private String separator;

        @Override
        public Interceptor build() {
            return new JsonInterceptor(fields,separator);
        }

        @Override
        public void configure(Context context) {
            fields = context.getString("fields");
            separator = context.getString("separator");
        }
    }
}
