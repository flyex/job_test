package com.flyex.demo;

import com.flyex.bean.User2;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class User2Demo {
    private static SessionFactory sessionFactory;

    public Integer addUser(String name,int paiming){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer userId = null;

        try{
            tx = session.beginTransaction();
            User2 user2 = new User2(paiming,name);
            userId = (Integer)session.save(user2);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userId;
    }

    public void showUser2(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            List list = session.createQuery("FROM User2 ").list();
            for(Iterator iterator = list.iterator();iterator.hasNext();){
                User2 user2 = (User2)iterator.next();
                System.out.print("排名是："+user2.getPaiming()+"---------");
                System.out.println("姓名："+user2.getName());
            }
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateUser2(int key,int new_paiming){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            User2 user2 = (User2)session.get(User2.class,key);
            user2.setPaiming(new_paiming);
            tx.commit();
        }catch (HibernateException ex){
            if(tx!=null) tx.rollback();
        }finally {
            session.close();
        }
    }

    public void deleteUser2(int key) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User2 user2 = (User2) session.get(User2.class, key);
            session.delete(user2);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
        }finally {
            session.close();
        }
    }
    //Use hql
    public List getUser2ByPaiMing(int rank){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select U.name from User2 U where U.paiming = :paiming";
        Query query = session.createQuery(hql);
        query.setParameter("paiming",rank);
        List<String> list = query.list();
        return list;
    }

    //User criteria  https://www.w3cschool.cn/hibernate/ugov1ie8.html
    public List getUser2ByPaiMing2(int rank){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User2.class);
        criteria.add(Restrictions.eq("paiming",rank));
        List list = criteria.list();
        session.close();
        return list;
    }


    public static void main(String[] args){
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            System.out.println("建立会话工厂失败"+ex);
            throw new ExceptionInInitializerError(ex);
        }
        User2Demo user2Demo = new User2Demo();
        //int userId1 = user2Demo.addUser("怪兽布达拉",1);
        //int userId2 = user2Demo.addUser("怪兽厄加特",2);
        //int userId3 = user2Demo.addUser("怪兽塔不达",5);

        //user2Demo.showUser2();
        //user2Demo.updateUser2(2,66);
        //user2Demo.deleteUser2(6);

        //System.out.println(user2Demo.getUser2ByPaiMing(4));
        List<User2> list = user2Demo.getUser2ByPaiMing2(5);
        for(Iterator<User2> iterator = list.iterator();iterator.hasNext();){
            User2 user2 = iterator.next();
            System.out.println(user2.getName()+":"+user2.getPaiming());
        }

    }
}
