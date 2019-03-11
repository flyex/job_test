package test.reflect.Method;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class MethodsTest2 {
    public static String getValue(String key) throws IOException{
        Properties pro = new Properties();
        FileReader in = new FileReader("method.txt");
        pro.load(in);
        in.close();
        return pro.getProperty(key);
    }

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName(getValue("className"));
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m:methods){
            System.out.println(m);
        }
        Method m = clazz.getMethod(getValue("go1"));
        m.invoke(clazz.getConstructor().newInstance());

        m = clazz.getDeclaredMethod(getValue("go4"));
       // m.invoke(clazz.getConstructor().newInstance(),new Object[]{new String[]{"1","2"}});

    }
}
