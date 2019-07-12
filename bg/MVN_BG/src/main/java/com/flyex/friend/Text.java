package com.flyex.friend;

import java.util.*;

public class Text {
    public static void main(String[] args) {
        ArrayList<String> as = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("e");
        sb.append("b");
        as.add("a");
        as.add("e");
        as.add("b");
        Collections.sort(as);

        String[] s = sb.toString().split(",");

        ArrayList<String> as2 = new ArrayList<String>();

        for (String s1:s){
            as2.add(s1);
        }



        System.out.println(as.toString());
        System.out.println(as2.toString());
    }
}
