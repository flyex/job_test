package com.test2.user2.demo;

import com.test2.user2.vo.User2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Demo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User2 user2 = session.get(User2.class,3);
        System.out.println(user2.getGrade());
        tx.commit();
        session.close();
    }
}
