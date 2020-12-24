package com.flyex.nouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        HashMap<Object, Object> objectHashMap = new HashMap<>();

        objectHashMap.put("aa","cc");
        objectHashMap.put(1,2);

        Set<Map.Entry<Object, Object>> entries = objectHashMap.entrySet();
        for (Map.Entry entry:entries){
            System.out.println(entry.getKey() + "`" + entry.getValue());
        }

    }
}
