package Thread;

public class TestQ {
    Object lock = new Object();

    public synchronized void go(){
            try {
                System.out.println(Thread.currentThread().getName() + "go");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    public static synchronized void go2(){
            try {
                System.out.println(Thread.currentThread().getName() + "go2");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    public static void main(String[] args) {
        TestQ tq = new TestQ();
        TestQ tq2 = new TestQ();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                tq.go2();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                tq2.go2();
            }
        });

        thread2.start();
        thread1.start();
    }
}
