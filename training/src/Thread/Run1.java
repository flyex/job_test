package Thread;

/**
 * Created by flyex on 2018/10/18.
 */
public class Run1 implements Runnable {
    public void run(){
        for(int i =1;i<100;i++){
            System.out.println("run1运行时"+i);
        }
    }
}
