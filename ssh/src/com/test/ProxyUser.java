package com.test;

public class ProxyUser implements UserService {
    private UserService userService;
    private Show show;
    public ProxyUser(UserService userService,Show show){
        this.userService = userService;
        this.show = show;
    }

    public void addUser(User user){
        show.begin();
        userService.addUser(user);
        show.close();
    }

    public void deleteUser(int uid){
        show.begin();
        userService.deleteUser(uid);
        show.close();
    }
}
