package thread.classLocked;

public class AcountOperator implements Runnable {
    private Acount acount;
    static float x;
    static float y;

    public AcountOperator(Acount a,float x,float y){
        acount = a;
        this.x = x;
        this.y = y;

    }

    public void run(){
            synchronized(acount){
                acount.deposit(x);
                System.out.println(Thread.currentThread().getName()+"------"+"余额："+acount.getBalance());
                acount.withdraw(y);
                System.out.println(Thread.currentThread().getName()+"------"+"余额："+acount.getBalance());
            }
    }
}
