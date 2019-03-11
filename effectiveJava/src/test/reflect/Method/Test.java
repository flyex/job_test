package test.reflect.Method;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        Class methodsclass = Class.forName("test.reflect.Method.Methods");
        Method[] methods = methodsclass.getMethods();//get public methods include super class and object class
        System.out.println("*******get public methods include super class and object class*********");
        for (Method m:methods){
            System.out.println(m);
        }

        methods = methodsclass.getDeclaredMethods();
        System.out.println("*********get all methods in this class*********");
        for (Method m:methods){
            System.out.println(m);
        }
        System.out.println("******get 'go1' method and invoking it*******");
        Method method = methodsclass.getDeclaredMethod("go1");
        Object o = methodsclass.getConstructor().newInstance( );
        method.invoke(o);

        System.out.println("*****get method with parameter and returned*******");
        method = methodsclass.getDeclaredMethod("go4", Boolean.class);
        method.setAccessible(true);
        Object return_param = method.invoke(o,true);
        System.out.println("返回值是："+return_param);
        System.out.println("*******可变参数类型方法*******");
        method = methodsclass.getDeclaredMethod("go5", String[].class);
        method.setAccessible(true);
        method.invoke(o,new Object[]{new String[]{"1","2","3"}} );
    }
}
