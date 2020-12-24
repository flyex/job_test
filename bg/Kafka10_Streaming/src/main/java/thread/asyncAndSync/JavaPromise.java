package thread.asyncAndSync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JavaPromise {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("son thread is started");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "son thread is finished";
            }
        }, executor);

//        future.thenAccept(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

        //lambada
        future.thenAccept(s -> System.out.println(s+"ok"));

        System.out.println("main thread is running");


        executor.shutdown();

    }
}
