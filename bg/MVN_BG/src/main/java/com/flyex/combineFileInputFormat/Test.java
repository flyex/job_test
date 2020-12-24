package com.flyex.combineFileInputFormat;

import java.util.concurrent.atomic.AtomicBoolean;

public class Test {

    public static void main(String[] args) {

        AtomicBoolean flag = new AtomicBoolean(true);

        for (int i = 0; i < 5; i++) {
            System.out.println(flag.compareAndSet(true,false));
        }
    }
}
