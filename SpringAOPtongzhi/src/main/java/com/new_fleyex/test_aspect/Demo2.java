package com.new_fleyex.test_aspect;

import com.new_fleyex.configuration.AspectJavaConfig;
import com.new_fleyex.cut_point.Performance;
import com.new_fleyex.cut_point_impl.Sing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AspectJavaConfig.class);
        Performance performance = (Performance) context.getBean("getSong");
        performance.perform(0);
    }
}
