package test.reflect.test;

import javax.naming.spi.ObjectFactoryBuilder;
import java.lang.reflect.Constructor;

public class ReflectTest {
    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("test.reflect.test.Student");
        System.out.println("获取共有构造方法");
        Constructor[] cons = clazz.getConstructors();
        for(Constructor c:cons){
            System.out.println(c);
        }

        System.out.println("获取本类中所有构造方法");
        cons = clazz.getDeclaredConstructors();
        for (Constructor c:cons){
            System.out.println(c);
        }

        System.out.println("获取共有无参构造方法");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。
        System.out.println(con);
        Object O = con.newInstance();
        //System.out.println(O);

        System.out.println("获取私有构造方法 可看到参数类型是int");
        con = clazz.getDeclaredConstructor(int.class);

        con.setAccessible(true);  //将私有修饰暴力访问 忽略private修饰符
        O = con.newInstance(12);


    }
}
