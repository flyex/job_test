package inclass;
import java.io.*;

/**
 * Created by flyex on 2018/9/5.
 */
class Out1 {
    private int age = 12;

    private class In {
        public void print() {
            System.out.println(age);
        }
    }
    public void out1Print() {
        new In().print();
    }
}