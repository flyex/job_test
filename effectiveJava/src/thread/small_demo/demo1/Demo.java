package thread.small_demo.demo1;

public class Demo {
    public static void main(String[] args) {
        ServicesModule services = new ServicesModule();
        new Thread(new Productor(services)).start();
        new Thread(new Customer(services)).start();
    }

}
