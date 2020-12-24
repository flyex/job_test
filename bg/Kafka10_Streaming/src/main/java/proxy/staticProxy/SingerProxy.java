package proxy.staticProxy;

public class SingerProxy implements Singer{
    private Singer singer;

    public SingerProxy(Singer proxy){
        this.singer = proxy;
    }
    @Override
    public void sing() {
        System.out.println("观众们好啊");
        singer.sing();
        System.out.println("谢谢观看");
    }

    @Override
    public void song() {
        System.out.println("emm");
    }
}
