package test;
import java.io.*;


/**
 * Created by flyex on 2018/9/5.
 */
public class EmployeeTest {

    public static void main(String[] args){
        Employee empOne = new Employee("张三");
        Employee empTwo = new Employee("李四");

        empOne.empAge(24);
        empOne.empDesignation("营销");
        empOne.empSalary(1000);
        empOne.printEmployee();

        empTwo.empAge(27);
        empTwo.empDesignation("售后");
        empTwo.empSalary(1200);
        empTwo.printEmployee();

    }
}
