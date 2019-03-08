package Overrideloader;

import java.util.Scanner;

/**
 * Created by flyex on 2018/9/27.
 */
 class OverLoaderTest {
    private static int a = 1;
    private static double b =1.1111111111;
    private static String c = "哈哈哈";
    void test(){
        System.out.println("66666");
    }
    void test(int x){
        System.out.println("整形");
    }

    void test(double x){
        System.out.println("双精度浮点");
    }

    void test(String x){
        System.out.println("字符串");
    }
    public static void main(String[] args){
        OverLoaderTest ride = new OverLoaderTest();
        ride.test();
        ride.test(a);
        ride.test(b);
        ride.test(c);
    }
}
