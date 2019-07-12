package com.datetime;

import com.datetime.bean.Timekeeper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;


public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("" +
                "D:\\project\\spring+hibernate\\resources\\spring.xml");
        Dao dao = (Dao)context.getBean("dao");
        Timekeeper t = new Timekeeper();
        t.setTimekeeperId("1");
        t.setInOut("1");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Date date = new Date();
        t.setDateTime(timestamp);
        dao.save(t);
    }
}
