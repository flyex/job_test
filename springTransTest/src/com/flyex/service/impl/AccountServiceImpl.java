package com.flyex.service.impl;

import com.flyex.dao.AccountDao;
import com.flyex.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer(String outer, String inner, int money) {
        accountDao.out(outer,money);
        accountDao.in(inner,money);
    }
}
