package com.test;

public class Test {
    public static void main(String[] args) {
        Show show = new Show();
        UserService userService = new UserServiceImpl();
        ProxyUser proxyUser = new ProxyUser(userService,show);
        proxyUser.addUser(null);
        proxyUser.deleteUser(0);
    }
}
