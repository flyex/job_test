package com.flyex.batch.setApi;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {

        ArrayList<HashMap<Integer, String>> lhashmap = new ArrayList<>();
        HashMap<Integer, String> h1 = new HashMap<>();
        HashMap<Integer, String> h2 = new HashMap<>();

        HashMap<Integer, String> h3 = new HashMap<>();

        h1.put(1,"as");
        h1.put(2,"asd");
        h2.put(3,"sadff");
        h2.put(4,"asdaa");

        lhashmap.add(h1);
        lhashmap.add(h2);

        System.out.println(lhashmap);

        for (HashMap map : lhashmap){
            h3.putAll(map);
        }

        System.out.println("---------------"+h3);

    }
}
