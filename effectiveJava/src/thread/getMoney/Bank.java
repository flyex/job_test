package thread.getMoney;

import java.util.Objects;

public class Bank  {
    static float money = 1000;

    private void Counter(float x){
        this.money -= x;
        System.out.println("柜台取钱"+x+"后还余额+"+money);
    }

    private void ATM(float x){
        this.money -= x;
        System.out.println("ATM取钱"+x+"后还余额"+money);
    }

    public synchronized void outMoney(float x,String mode) throws Exception{
        if(x>Bank.money){
            throw new Exception("取款金额为："+x+"余额为："+money);
        }
        if (Objects.equals(mode,"ATM")){
            ATM(x);
        }else {
            Counter(x);
        }
    }
}
