package com.flyex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Configuration
//@EnableWebMvc     //启用spring MVC
//@ComponentScan("com.flyex.web")    //启用组件扫描
public class WebConfig /**extends WebMvcConfigurerAdapter**/ {
    /**@Bean
    public ViewResolver viewResolver(){     //配置试图解析器
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");      //设置前缀
        resolver.setSuffix(".jsp");     //设置后缀
        resolver.setExposeContextBeansAsAttributes(true);       //expose 暴露 揭发
        return resolver;
    }

    @Override       //配置静态资源处理
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }**/
}
