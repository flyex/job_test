package thread.classLocked;

public class Demo {
    public static void main(String[] args){
        Acount acount = new Acount("小马",1000);
        AcountOperator acountOperator = new AcountOperator(acount,1000,400);

        Thread thread = new Thread(acountOperator,"aa");
        Thread thread2 = new Thread(acountOperator,"bb");
        thread.start();
        thread2.start();
    }
}
