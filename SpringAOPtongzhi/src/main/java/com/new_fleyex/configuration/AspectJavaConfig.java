package com.new_fleyex.configuration;

import com.new_fleyex.aspect.Audience;
import com.new_fleyex.aspect.CountPerform;
import com.new_fleyex.cut_point.Performance;
import com.new_fleyex.cut_point_impl.Sing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@Component
//@EnableAspectJAutoProxy   //一定要开启切面自动代理  否则bean只能是个普通bean
public class AspectJavaConfig {

    @Bean
    public Audience getAudience(){
        return new Audience();
    }

    @Bean
    public Performance getSong(){
        Sing sing = new Sing();
        List<String> list = new ArrayList<>();
        list.add("国歌");
        list.add("江南");
        list.add("晴天");
        sing.setCatalog(list);
        return sing;
    }

    //@Bean
    public CountPerform getCountPerform(){
        return new CountPerform();
    }


}
