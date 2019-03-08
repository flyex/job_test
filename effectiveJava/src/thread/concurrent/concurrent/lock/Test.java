package thread.concurrent.concurrent.lock;

public class Test {
    public static void main(String[] args){
        LockTest lockTest = new LockTest();
        Thread thread1 = new Thread(lockTest);
        Thread thread2 = new Thread(lockTest);

        thread1.start();
        thread2.start();

    }
}
