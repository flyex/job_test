package com.flyex.service;

import com.flyex.dao.AccountDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public class ThreadGetMoney implements Runnable {
    private AccountDao accountDao;

    String name;
    int money;

    public ThreadGetMoney(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public void run() {
        if (accountDao.getMoney()>money){
            accountDao.out(name,money);
            System.out.println("成功取出"+money+"元");
        }else {
            System.out.println("余额不足,当前余额"+accountDao.getMoney()+"元");
        }
    }
}
