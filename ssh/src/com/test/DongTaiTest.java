package com.test;

import java.lang.reflect.Proxy;

public class DongTaiTest {
    Object target = new UserServiceImpl();
    Show show = new Show();
    DongTaiProxy proxy = new DongTaiProxy(target,show);
    UserService userService = (UserService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),proxy);


}
