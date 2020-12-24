package com.flyex.testNoUse;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class MainReflect {

    public static void main(String[] args) throws Exception {

        //Class<ReflectTest> rc = ReflectTest.class;

        Class<?> rcc = Class.forName("com.flyex.testNoUse.ReflectTest");

        Object rccc = rcc.newInstance();
        Object rcccc = rcc.newInstance();


    }
}
