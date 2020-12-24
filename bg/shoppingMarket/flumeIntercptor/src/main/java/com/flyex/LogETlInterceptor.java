package com.flyex;

import com.flyex.utils.LogUtils;
import org.apache.commons.lang.CharSet;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LogETlInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody(), Charset.forName("UTF-8"));

        if (LogUtils.validate(body)){
            return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        ArrayList<Event> interceptors = new ArrayList<>();

        for (Event event : list){
            Event yes = intercept(event);
            if (yes != null){
                interceptors.add(yes);
            }
        }
        return interceptors;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new LogETlInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
