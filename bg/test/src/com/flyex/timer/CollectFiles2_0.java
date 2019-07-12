package com.flyex.timer;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CollectFiles2_0 {
    public static void main(String[] args) throws Exception {
        File srcdir = new File("D:\\project\\test");

        File[] listFiles2 = srcdir.listFiles();
        /**File[] listFiles = srcdir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith("a")){
                    return true;
                }
                return false;
            }
        });**/
        File[] list = null;
        List list2 = new ArrayList<>();
        for (int i=0;i<listFiles2.length;i++){

            list = listFiles2[i].listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.startsWith("acc")){
                        return true;
                    }
                    return false;
                }
            });
            list2.add(list);
        }
        for (Object o:list2){
            File[] f = (File[])o;
            for (File e:f){
                System.out.println(e.toString());
            }
        }
    }
}
