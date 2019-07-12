package com.many_to_many.demo;

import com.many_to_many.authority.bean.Authority;
import com.many_to_many.user.bean.User;
import com.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        /*
        多对多操作
         */
        /** User user1 = new User();
        user1.setUsername("小华");
        User user2 = new User();
        user2.setUsername("小李");

        Authority a1 = new Authority();
        a1.setaName("超级管理员权限");
        Authority a2 = new Authority();
        a2.setaName("普通用户权限");

        user1.getAuthorities().add(a1);
        user1.getAuthorities().add(a2);
        a1.getUsers().add(user1);
        a2.getUsers().add(user1);

        user2.getAuthorities().add(a2);
        a2.getUsers().add(user2);

        session.save(user1);
        session.save(user2);
        session.save(a1);
        session.save(a2);**/
        /*
           格式化list
         */
        /**Criteria ce = session.createCriteria(User.class);
        ProjectionList pList = Projections.projectionList();
        pList.add(Property.forName("uId"));
        pList.add(Property.forName("username"));
        ce.setProjection(pList);
        List<Object[]> list = ce.list();
        for (Object[] objects:list){
            for (Object o:objects){
                System.out.print(o+"\t");
            }
        }**/
        /*
        一级缓存的存在 mysql只会查询一次
         */
        User user1 = session.get(User.class,1);


        tx.commit();
        session.close(); //session关闭后 二级缓存起作用

        Session session2 = HibernateUtil.currentSession();
        Transaction tx2 = session2.beginTransaction();
        /*
        如果查询的是1 则只有一条sql语句
         */
        User user2 = session2.get(User.class,2);

        tx2.commit();
        session2.close();

    }
}
