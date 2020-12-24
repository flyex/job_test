package proxy.dynamicProxy.cglib;

import sun.misc.Launcher;

import java.net.URL;

public class TestCglib {
    public static void main(String[] args) {

        Sing sing = new Sing();
        CglibProxyFactory cg = new CglibProxyFactory(sing);
        Sing proxy = (Sing) cg.getProxyInstance();
        proxy.song();
        proxy.songAgain();


    }
}
