package com.test;

public class UserServiceImpl implements UserService {
    public void addUser(User user){
        System.out.println("增加一个用户");
    }
    public void deleteUser(int uid){
        System.out.println(":删除一个用户");
    }
}
