package thread.classLocked;

public class Acount {
    private String name;
    private float amount;

    public Acount(String name,float amount){
        this.name = name;
        this.amount = amount;
    }

    public void deposit(float money){
        amount += money;
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void withdraw(float money){
        amount -= money;
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public float getBalance(){
        return amount;
    }
}
