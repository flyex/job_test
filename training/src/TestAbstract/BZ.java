package TestAbstract;

/**
 * Created by flyex on 2018/9/28.
 */
public abstract class BZ {
    private double chang;
    private double kuan;
    public BZ(double x,double y){
        System.out.println("初始化长方形");
        this.chang = x;
        this.kuan = y;
    }
    public double getMianJi(){
        return chang*kuan;
    }

}
