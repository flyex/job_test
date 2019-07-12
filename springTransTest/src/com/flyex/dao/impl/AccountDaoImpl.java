package com.flyex.dao.impl;

import com.flyex.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao  {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void in(String inner, int money) {
        this.getJdbcTemplate().update(
                "update account_bank set money=money+? where name=?",money,inner);
        int i = 1/0;

    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void out(String outer, int money) {
        this.getJdbcTemplate().update(
                "update account_bank set money=money-? where name=?",money,outer
        );
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int getMoney() {
       return this.getJdbcTemplate().queryForObject(
               "select money from account_bank",Integer.class);
    }
}
