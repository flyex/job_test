package test.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class RemoveLimit {
    public static void main(String[] args) throws Exception {
        ArrayList<String> array = new ArrayList<String>();
        array.add("as");
        array.add("asd");
        //array.add(11);
        Class arrclazz = array.getClass();
        Method m = arrclazz.getMethod("add", Object.class);
        m.invoke(array,100);

        for(Object o:array){
            System.out.println(o);
        }
    }
}
