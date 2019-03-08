package thread.joinTest.Test1;
//Join方法实现是通过wait（小提示：Object 提供的方法）。 当main线程调用t.join时候，main线程会获得线程对象t的锁（
// wait 意味着拿到该对象的锁),调用该对象的wait(等待时间)，直到该对象唤醒main线程，比如退出后。

public class Test {
    public static void main(String[] args){
        Thread t = new Thread(new RunnableImpl());
        new MyThread(t).start();
        t.start();
        try {
            t.join(1000);  //main无法拿到t进程的锁，等待t休眠完成才能拿到锁
            System.out.println("join finished");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
