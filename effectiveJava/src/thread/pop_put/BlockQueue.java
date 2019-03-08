package thread.pop_put;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockQueue implements Runnable {
    private List list = new ArrayList();

    public synchronized void pop() throws InterruptedException {
        for (int i = 0; i <10; i++) {
            while (list.size() == 0) {
                this.notify();
                System.out.println("数组元素空，等待进栈,我休眠了");
                System.out.println(Thread.currentThread().getName());
                this.wait();

            }
            if (list.size() > 0) {
                System.out.println("正在出栈");
                list.remove(0);
            }
        }
    }

    public synchronized void put() {
        for (int i = 0; i < 4; i++) {
            list.add("1");
            list.add("1");
            list.add("1");
            System.out.println("正在进栈,我要唤醒pop了");
            System.out.println(Thread.currentThread().getName());
            this.notify();
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

        @Override
        public void run() {
                String threadName = Thread.currentThread().getName();
                if (threadName.equals("pop")) {
                    try {
                        pop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (threadName.equals("put")) {
                    put();
                }
            }


    }

