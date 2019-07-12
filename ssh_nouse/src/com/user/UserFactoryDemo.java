package com.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserFactoryDemo {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao user = (UserDao)context.getBean("userStaticFactory");
        user.say();
    }
}
