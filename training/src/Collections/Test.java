package Collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by flyex on 2018/10/29.
 */
public class Test {
    public static void main(String[] args){
        HashSet collection = new HashSet();
        String str1 = "dddd";
        String str2 = "ssss";
        collection.add(str1);
        collection.add(str2);
        Iterator itr = collection.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("长度是"+collection.size());
    }
}
