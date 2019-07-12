package com.test.kelei.dao;

import com.test.kelei.vo.Kelei;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class KeleiDao {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Kelei kelei = session.get(Kelei.class,2);
        System.out.println(kelei);
        System.out.println(kelei.getWuzhong());

    }
}
