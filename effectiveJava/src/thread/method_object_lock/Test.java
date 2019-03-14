package thread.method_object_lock;

import javax.print.attribute.standard.RequestingUserName;

public class Test {
    private int a = 0;
    private int b = 0;

    private Object lock1 = new Object();
    private Object lock2 = new Object();


    public void add() {
        synchronized (lock1) {
            for (int i = 0; i < 1000; i++) {
                System.out.println(++a);
            }
        }
    }

    public void minus() {
        synchronized (lock2) {
            for (int i = 0; i < 1000; i++) {
                System.out.println(--b);
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(){
            @Override
            public void run() {
                    test.add();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                    test.minus();
            }
        }.start();

    }
}
