package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

    public static void main(String[] args) {

        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        try {
            blockingQueue.offer(1,2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(blockingQueue.size());
    }
}
