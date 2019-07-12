package com.datetime;

import com.datetime.bean.Timekeeper;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Dao extends HibernateDaoSupport {
    public void save(Timekeeper timekeeper){
        getHibernateTemplate().save(timekeeper);
    }
}
