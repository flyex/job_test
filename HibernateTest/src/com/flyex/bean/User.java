package com.flyex.bean;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String userName;
    private int age;
    /**public User(){}
    public User(String userName,int id,int age){
        this.userName = userName;
        this.age=age;
        this.id=id;
    }**/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
