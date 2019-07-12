package com.flyex;

import config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloProgram {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorldService service = (HelloWorldService) context.getBean("helloWorldService");
        service.setMessage("hh");

        service.go();
        service.say();

        HelloWorldService service2 = (HelloWorldService) context.getBean("helloWorldService");
        service2.go();
        service2.say();
    }
}
