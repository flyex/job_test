package TestAbstract;

/**
 * Created by flyex on 2018/9/28.
 */
public class JiSuan {
    public static void main(String[] args){
        ChangFX c = new ChangFX();
        c.setChang(2.5);
        c.setKuan(3.5);
        System.out.println("长方形面积是："+c.mianji());
        System.out.println("长方形周长是："+c.zhouchang());
    }
}
