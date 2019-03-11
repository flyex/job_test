package test.reflect.Method;

import java.lang.reflect.Method;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("test.reflect.Method.MainMethod");
        Method method = clazz.getMethod("main", String[].class);
        method.invoke(null,(Object)new String[]{"6","2","3"});
    }
}
