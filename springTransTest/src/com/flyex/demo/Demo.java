package com.flyex.demo;

import com.flyex.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        
        ApplicationContext context = new
                FileSystemXmlApplicationContext("D:\\project\\springTransTest\\resources\\spring.xml");
        AccountService accountService = (AccountService)context.getBean("accountService");
        accountService.transfer("小牛","小芳",1000);
    }
}
