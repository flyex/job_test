package thread.getMoney;

public class Demo {
    public static void main(String[] args){
        Bank bank = new Bank();
        PersonA p = new PersonA(bank,"Counter",500);
        PersonA p2 = new PersonA(bank,"ATM",600);

        p.start();
        p2.start();
    }
}
