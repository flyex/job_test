package proxy.staticProxy;

public class Test {
    public static void main(String[] args) {

        Singer proxy = new SingerHello();
        Singer target = new SingerProxy(proxy);
        target.sing();
    }
}
