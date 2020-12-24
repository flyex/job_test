package ProducerAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueuePattern {
    //设置阻塞队列容量
    private static final int MAX_CAPACITY = 10;
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(MAX_CAPACITY);
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public void producer() throws InterruptedException{
        while (flag){
            boolean retvalue = blockingQueue.offer(atomicInteger.incrementAndGet(), 2, TimeUnit.SECONDS);

            if (retvalue == true){
                System.out.println(Thread.currentThread().getName()+ "\t 插入队列"+ atomicInteger.get()+ "成功，队列大小为"+ blockingQueue.size());
            }else {
                System.out.println(Thread.currentThread().getName()+ "\t 插入队列"+ atomicInteger.get()+ "失败，队列大小为"+ blockingQueue.size());
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+ "flag为false，生产停止");
    }

    public void consumer() throws InterruptedException {
        Integer res = null;
        while (true){
            res = blockingQueue.poll(2, TimeUnit.SECONDS);
            while (null == res){
                System.out.println("超过两秒没取到数据，即将退出消费者");
                return;
            }
            System.out.println(Thread.currentThread().getName()+ "消费"+ res + "成功,当前队列大小为"+ blockingQueue.size());
            Thread.sleep(3000);
        }
    }

    public void stop(){
        flag = false;
    }

    public static void main(String[] args) {
        BlockingQueuePattern queuePattern = new BlockingQueuePattern();

        new Thread(()->{
            try {
                queuePattern.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            try {
                queuePattern.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //queuePattern.stop();
    }
}
