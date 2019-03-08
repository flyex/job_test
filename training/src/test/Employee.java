package test;
import java.io.*;

/**
 * Created by flyex on 2018/9/5.
 */
public class Employee {
    static int b;
    String name;
    int age;
    String designation;
    double salary;


    public Employee(String name){

        this.name=name;

    }


    public void empAge(int emAge){
        age=emAge;
    }

    public void empDesignation(String empDesig){
        designation = empDesig;
    }
    public void empSalary(double empSalary){
        salary=empSalary;
    }

    public void printEmployee(){
        System.out.println("名字："+ name);
        System.out.println("年龄："+ age);
        System.out.println("职位："+designation);
        System.out.println("薪水："+ salary);
        System.out.println();
    }
}
