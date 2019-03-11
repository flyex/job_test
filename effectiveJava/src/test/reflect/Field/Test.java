package test.reflect.Field;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception {
        Class stuclass = Class.forName("test.reflect.Field.Students");

        System.out.println("******get all public field********");
        Field[] fields = stuclass.getFields();
        for (Field f:fields){
            System.out.println(f);
        }
        System.out.println("******get all public and protect and default fields in this class******");
        fields = stuclass.getDeclaredFields();
        for (Field f:fields){
            System.out.println(f);
        }
        System.out.println("******get all appointed public field ");
        Field f = stuclass.getField("age");

        Object o = stuclass.getConstructor().newInstance(); //get a new object
        f.set(o,12);
        Students students = (Students)o;
       // System.out.println("student's age is:"+students.age);
        f = stuclass.getDeclaredField("name");
        //f.setAccessible(true);
        f.set(o,"花花");

        f = stuclass.getDeclaredField("numb");
        f.setAccessible(true);
        f.set(o,"18819129111");

        f = stuclass.getDeclaredField("sex");
        //f.setAccessible(true);
        f.set(o,'女');

        System.out.println(o);


    }
}
