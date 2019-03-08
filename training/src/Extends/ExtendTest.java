package Extends;

/**
 * Created by flyex on 2018/9/27.
 */
public class ExtendTest extends Test {
    void c1(){
        super.c();
    }
    public static void main(String[] args){
        ExtendTest t = new ExtendTest();
        t.c();
        System.out.println(t.d(2));
        System.out.println(t.e());
        t.f(3);
        System.out.println(t.a);
    }
}
