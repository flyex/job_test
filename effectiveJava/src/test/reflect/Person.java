package test.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name,int age){
        this.name=name;
        this.age= age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args)throws Exception {
        Person p =new Person("hh",12);

        Class clazz = p.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            String key = field.getName();
            PropertyDescriptor descriptor = new PropertyDescriptor(key,clazz);
            Method method = descriptor.getReadMethod();
            Object value = method.invoke(p);

            /**Method method2 = descriptor.getWriteMethod();
            Object name_1 = "hhh";
            method2.invoke(name_1);**/


            System.out.println(key+":"+value);

        }
    }
}