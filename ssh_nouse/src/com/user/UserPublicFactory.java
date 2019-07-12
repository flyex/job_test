package com.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserPublicFactory {
    public UserPublicFactory(){
        System.out.println("实例工厂方法");
    }
    public UserDao getInstance(){
        UserDao user = new UserDao();
        return user;
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext");
        UserDao user = (UserDao)context.getBean("instance");
        user.say();
    }
}
