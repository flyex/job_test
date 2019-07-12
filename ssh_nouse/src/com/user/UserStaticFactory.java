package com.user;

import org.springframework.stereotype.Component;

@Component("userStaticFactory")
public class UserStaticFactory {
    public UserStaticFactory(){
        System.out.println("静态工厂方法");
    }
    public static UserDao getInstance(){
        return new UserDao();
    }
}
