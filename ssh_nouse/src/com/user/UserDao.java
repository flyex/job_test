package com.user;

import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDao {
    public UserDao(){
        System.out.println("小老板");
    }
    String name;
    String age;
    void say(){
        System.out.println("我活了");
    }
}
