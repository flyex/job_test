package com.flyex.dao;

import com.flyex.pojo.User2;
import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public class UserDao extends HibernateDaoSupport {
    public void sava(User2 user2){
        getHibernateTemplate().save(user2);
    }

    public User2 findByPaiming(int paiming){
        DetachedCriteria criteria = DetachedCriteria.forClass(User2.class);
        criteria.add(Restrictions.eq("paiming",paiming));
        List<User2> uList = (List<User2>) this.getHibernateTemplate().findByCriteria(criteria);
        return uList.get(0);
    }

    public void update(User2 user2){
        getHibernateTemplate().update(user2);
    }

    public List<User2> findLogin(int p,String username){
        DetachedCriteria criteria = DetachedCriteria.forClass(User2.class);

        criteria.add(Restrictions.eq("paiming",p));
        criteria.add(Restrictions.eq("username",username));
        List<User2> uList =(List<User2>) getHibernateTemplate().findByCriteria(criteria);

        return uList;

    }


}