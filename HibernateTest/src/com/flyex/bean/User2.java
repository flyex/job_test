package com.flyex.bean;

public class User2 {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int paiming;
    private String name;
    public User2(){}
    public User2(int n_paiming,String n_name){
        this.paiming = n_paiming;
        this.name = n_name;
    }

    public int getPaiming() {
        return paiming;
    }

    public void setPaiming(int paiming) {
        this.paiming = paiming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
