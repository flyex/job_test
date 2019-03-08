package thread.concurrent.volatileTest;

public class Test3 {
    public int acount = 0;

    public synchronized void add(){
        acount++;
    }

    public static void main(String[] args){
        Test3 test3 = new Test3();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run(){
                    for (int j =0;j<1000;j++){
                        test3.add();
                    }
                }
            }.start();
        }
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test3.acount);
    }
}
