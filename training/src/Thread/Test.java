package Thread;

import java.util.concurrent.*;

/**
 * Created by flyex on 2018/10/17.
 */
public class Test {
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(5);
        TaskRunnable task = new TaskRunnable();
        TaskRunnable2 task2 = new TaskRunnable2();
        service.submit(task);
        System.out.println("再获取一个线程对象");
        service.submit(task);
        //service.submit(task);
        //service.submit(task);
        //service.submit(task);
        //service.submit(task2);
        service.shutdown();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
         ExecutorService service1 = Executors.newFixedThreadPool(4);
    }
}
