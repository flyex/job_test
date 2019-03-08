package com.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


public class UserDaoDemo {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao)context.getBean("userDao");
        UserDao userDao1 = (UserDao)context.getBean("userDao");
        userDao.say();
        userDao1.say();
    }
}
