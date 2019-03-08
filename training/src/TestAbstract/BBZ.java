package TestAbstract;

import java.security.PublicKey;

/**
 * Created by flyex on 2018/9/28.
 */
public class BBZ extends BZ {
    private int b;
    public BBZ(double x,double y,int z){
        super(x,y);
        setB(z);
    }
    public void setB(int x){
        if(x>0){
            b=x;
        }else{
            System.out.println("服了你");
            b=x;
        }
    }
    public static void main(String[] args){
        BBZ b = new BBZ(2.5,2.5,2);
        System.out.println("zhengshimianji");
    }
}
