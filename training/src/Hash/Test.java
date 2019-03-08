package Hash;

import java.util.*;

/**
 * Created by flyex on 2018/10/29.
 */
public class Test {
    public static void main(String[] args){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("123","adasd");
        map.put("3423","gdfgdf");
        map.put("ada","dfsfs");

        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry<String,String> map1 = it.next();
            System.out.println(map1.getKey() + "  " + map1.getValue());
        }
    }
}
