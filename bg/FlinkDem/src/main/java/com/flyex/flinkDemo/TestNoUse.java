package com.flyex.flinkDemo;


import org.apache.flink.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Collections;

public class TestNoUse {
    public static void main(String[] args) {

        ArrayList<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(4);
        num.add(7);

        Integer max = Collections.max(num);

        System.out.println(max);

    }
}
