package Thread;

/**
 * Created by flyex on 2018/10/18.
 */
public class ThreadTest {
    public static void main(String[] args){
        Run1 run1 = new Run1();
        Run2 run2 = new Run2();

        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);

        t1.run();
        t2.run();
    }
}
