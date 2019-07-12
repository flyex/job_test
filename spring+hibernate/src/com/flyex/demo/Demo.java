package com.flyex.demo;

import com.flyex.dao.UserDao;
import com.flyex.pojo.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("D:\\project\\spring+hibernate\\resources\\spring.xml");
        UserDao userDao = (UserDao)context.getBean("userDao");
        User2 user2 = new User2();
        user2.setPaiming(112);
        user2.setUsername("zg");
        //userDao.sava(user2);

        User2 user21 = userDao.findByPaiming(13);
        System.out.println(user21.getUsername());
        //user21.setUsername("new");
        //userDao.update(user21);

        List<User2> list = userDao.findLogin(1,"小宁");
        for (User2 user22:list){
            System.out.println("啊哈："+user22.getUsername());
        }
    }
}
