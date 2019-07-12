package com.test.wuzhong.dao;

import com.test.kelei.vo.Kelei;
import com.test.wuzhong.vo.Wuzhong;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Set;

public class WuzhongDao {
    public static void main(String[] args) {
        /**ServiceRegistry serviceRegistry =new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        **/
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Wuzhong wuzhong = session.get(Wuzhong.class,2);

        System.out.println(wuzhong);
        Set<Kelei> kSets = wuzhong.getKeleis();
        for (Kelei kelei:kSets){
            System.out.println(kelei);
        }

        tx.commit();
        session.close();
    }

}
