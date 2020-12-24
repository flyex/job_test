package com.jlyex;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
二分法
 */
public class Test2 {

    @Test
    public void test(){

        String ss = "wewe";
        String s2 = new StringBuilder("we").append("we").toString();

        int ssHash = ss.hashCode();
        System.out.println(s2.hashCode() == ssHash);
    }



    public static void main(String[] args) {
        String str = "111adasdasd&adasd'asdad/dasd1111asdadas\\asdada|asdasdadas";

        String newStr = str.replaceAll("&|'|/|\\\\|\\||\\d", " ");
        int index = StringUtils.indexOf("lalalalalademaxiya", "ad");
        System.out.println(index);


        StringBuilder sb = new StringBuilder();
        sb.append("s").append("sd").append("asda");
        System.out.println(sb);
    }
}
