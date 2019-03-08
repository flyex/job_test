package TestAbstract;

/**
 * Created by flyex on 2018/9/28.
 */
public class ChangFX extends JuXing {
    private double chang;
    private double kuan;

    public double zhouchang(){
        return 2*(chang+kuan);
    }
    public double mianji(){
        return chang*kuan;
    }

    public void setChang(double x){
        chang = x;
    }
    public void setKuan(double x){
        kuan = x;
    }


}
