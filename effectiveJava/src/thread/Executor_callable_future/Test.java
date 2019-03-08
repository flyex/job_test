package thread.Executor_callable_future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("开始运行");
        int taskSize = 5;

        Date date1 = new Date();
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        List<Future> l = new ArrayList<Future>();
        for(int i = 0;i<taskSize;i++){
            Callable c = new MyCallable(i+" ");
            Future f = pool.submit(c);
            l.add(f);
            Thread.sleep(3000);
        }

        pool.shutdown();

        for(Future f:l){
            System.out.println(f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("程序全部运行完毕，耗时："+(date2.getTime()-date1.getTime())+"毫秒");
    }
}
