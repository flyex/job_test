package thread.getMoney;

public class PersonA extends Thread{
    Bank bank;
    String mode;
    float x;

    public PersonA(Bank bank,String mode,float x){
        this.bank = bank;
        this.mode = mode;
        this.x = x;
    }

    public void run(){
        try {
            bank.outMoney(x, mode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
