package inclass;

/**
 * Created by flyex on 2018/9/5.
 */
public class Demo1 {
    public static void main(String[] args) {
        //此方法无效
        /*
        Out.In in = new Out().new In();
        in.print();
        */
        Out1 out = new Out1();
        out.out1Print();
    }
}