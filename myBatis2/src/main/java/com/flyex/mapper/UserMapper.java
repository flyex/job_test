package com.flyex.mapper;

import com.flyex.bean.User;

import java.util.List;

public interface UserMapper {
    void add(User user);
    List<User> findUserAll();
    List<User> findByName(String name);
}
