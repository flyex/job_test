package tyoe;

/**
 * Created by flyex on 2018/9/7.
 */
import java.io.*;
public class Employee{
    // 这个实例变量对子类可见
    public String name;
    public String des;
    // 私有变量，仅在该类可见
    private double salary;
    //在构造器中对name赋值
    public Employee (String empName,String empDes){
        name = empName;
        des = empDes;
    }
    //设定salary的值
    public void setSalary(double empSal){
        salary = empSal;
    }
    // 打印信息
    public void printEmp(){
        System.out.println("名字和描述 : "+name+"\t"+des );
        System.out.println("薪水 : " + salary);
    }

    public static void main(String[] args){
        Employee empOne = new Employee("RUNOOB","德玛西亚");
        empOne.setSalary(1000);
        empOne.printEmp();
    }
}