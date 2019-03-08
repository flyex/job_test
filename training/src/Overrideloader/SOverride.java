package Overrideloader;

/**
 * Created by flyex on 2018/9/27.
 */
public class SOverride extends FOverride {
    void test1(){
        System.out.println("子类输出");
        int a =1;
        System.out.println(a);
    }
    public static void main(String[] args){
        FOverride s = new SOverride();
        s.test1();
    }
}
