package Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by flyex on 2018/10/18.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        MyCallable c = new MyCallable(100,200);
        MyCallable c1 = new MyCallable(10,40);

        Future<Integer> result = threadPool.submit(c);
        Integer sum = result.get();
        System.out.println("sum="+sum);


    }
}
