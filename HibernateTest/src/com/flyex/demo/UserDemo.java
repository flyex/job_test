package com.flyex.demo;

import com.flyex.bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDemo {
    public  static void main(String[] args){
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User user = new User();
        user.setAge(18);
        user.setId(2019);
        user.setUserName("瑞文");

        session.save(user);

        tx.commit();

        session.close();

        sessionFactory.close();
    }
}
