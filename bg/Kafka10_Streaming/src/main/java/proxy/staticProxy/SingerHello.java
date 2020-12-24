package proxy.staticProxy;

public class SingerHello implements Singer {

    @Override
    public void sing() {
        System.out.println("hello how are you");
    }

    @Override
    public void song() {
        System.out.println("i am song");
    }
}
