package thread.sellTickets;

public class Ticket {

    int tickets = 10;
    boolean flag = true;

    public void sell() {
        while (flag) {
            synchronized (this){
                while (tickets <= 0){
                    flag = false;
                    System.out.println(Thread.currentThread().getName()+"发现票已售完");
                    try {
                        Thread.currentThread().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"开始卖第"+ tickets--+ "张票" +
                        "，还剩"+ tickets+ "张票");
            }
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{
            ticket.sell();
        },"AAA").start();

        new Thread(()->{
            ticket.sell();
        },"BBB").start();

    }
}
