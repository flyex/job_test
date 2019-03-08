package Thread;

/**
 * Created by flyex on 2018/10/17.
 */
public class TaskRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("自定义线程任务在执行"+i);
        }
    }
}
