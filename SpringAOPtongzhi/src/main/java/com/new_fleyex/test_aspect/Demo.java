package com.new_fleyex.test_aspect;

import com.new_fleyex.aspect.CountPerform_xml;
import com.new_fleyex.cut_point.Performance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("D:\\project\\SpringAOPtongzhi\\src\\main\\resources\\new_flyex\\spring-aop.xml");
        Performance p = (Performance) context.getBean("sing");
        /**CountPerform_xml countPerform_xml = (CountPerform_xml)context.getBean("countperform_xml") ;
        p.perform(1);
        p.perform(1);
        p.perform(2);
        p.perform(3);
        System.out.println(countPerform_xml.getCount(1));**/

        p.perform(0);
    }
}
