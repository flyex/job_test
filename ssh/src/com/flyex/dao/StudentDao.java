package com.flyex.dao;

import com.flyex.bean.User2;
import com.flyex.utils.PageHibernateCallback;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class StudentDao extends HibernateDaoSupport {
    public void add(User2 user2){
        getHibernateTemplate().save(user2);
    }

    public List findBy(int paiming,String username){
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select U.id from User2 U where U.paiming= :paiming and " +
                "U.username= :username";
        Query query = session.createQuery(hql);
        query.setParameter("paiming",paiming);
        query.setParameter("username",username);
        List<Integer> list = query.list();
        if (list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public User2 findByUsername(String username){
        DetachedCriteria criteria = DetachedCriteria.forClass(User2.class);
        criteria.add(Restrictions.eq("username",username));
        List<User2> uList = (List<User2>)this.getHibernateTemplate().findByCriteria(criteria);
        if (uList!=null&&uList.size()>0){
            return uList.get(0);
        }
        return null;
    }

    public User2 login(User2 user2){
        DetachedCriteria criteria = DetachedCriteria.forClass(User2.class);
        criteria.add(Restrictions.eq("paiming",user2.getPaiming()));
        criteria.add(Restrictions.eq("username",user2.getUsername()));
        List<User2> uList = (List<User2>)getHibernateTemplate().findByCriteria(criteria);
        if(uList!=null&&uList.size()>0){
            return uList.get(0);
        }
        return null;
    }
    //查找到所有用户
    public List<User2> findAll(){
        DetachedCriteria criteria = DetachedCriteria.forClass(User2.class);
        List<User2> ulist = (List<User2>)getHibernateTemplate().findByCriteria(criteria);
        return ulist;
    }
    //查询所有用户的个数
    public int findCountId(){
        String hql = "select count(*) from User2 ";
        List<Long> longList =(List<Long>) getHibernateTemplate().find(hql);
        if(longList != null && longList.size() > 0){
            return longList.get(0).intValue();
        }
        return 0;
    }
    //分页方法
    public List<User2> findAllPage(int begin,int limit){
        String hql = "from User2";
        List<User2> ulist = getHibernateTemplate().execute(new PageHibernateCallback<User2>(hql,begin,limit));
        if(ulist != null && ulist.size() > 0){
            return ulist;
        }
        return null;
    }
}
