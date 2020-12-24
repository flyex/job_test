package thread.asyncAndSync;

import java.util.concurrent.*;

public class JavaFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("thread is running");
                Thread.sleep(3000);
                System.out.println("thread is finished");
                return "hello";
            }
        });

        System.out.println(future.get());
        System.out.println("main is finished");

    }
}
