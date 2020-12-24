package com.flyex.test;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class MapList {

    public static void main(String[] args) {
        HashMap<Integer, String> v1 = new HashMap<Integer, String>();

        v1.put(1,"2ha");
        v1.put(2,"3ha");
        v1.put(3,"4");
        v1.put(4,"5");

        LinkedList<Map.Entry<Integer, String>> l1 = new LinkedList<Map.Entry<Integer, String>>(v1.entrySet());

        Collections.sort(l1, new Comparator<Map.Entry<Integer, String>>() {
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return (int) o2.getValue().hashCode()-o1.getValue().hashCode();
            }
        });

        System.out.println(l1.get(0)+"|"+l1.get(1)+"|"+l1.get(2));
    }
}
