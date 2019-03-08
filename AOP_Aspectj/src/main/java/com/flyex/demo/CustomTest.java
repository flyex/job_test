package com.flyex.demo;

import com.flyex.services.Custom;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CustomTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        Custom custom = (Custom) context.getBean("customImpl");
        //custom.goShopping();
        custom.buy();
        /**try {
            custom.havaException();
            //custom.addCustom("大润发");

        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
