package com.flyex.dao;

public interface AccountDao {
    public void out(String outer,int money);
    public void in(String inner,int money);
    public int getMoney();
}