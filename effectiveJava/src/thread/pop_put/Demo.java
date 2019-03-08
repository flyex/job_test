package thread.pop_put;

public class Demo {
    public static void main(String[] argss){
        BlockQueue blockQueue = new BlockQueue();
        Thread thread1 = new Thread(blockQueue,"pop");
        Thread thread2 = new Thread(blockQueue,"put");

        thread1.start();
        thread2.start();
    }
}
