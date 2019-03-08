/**
 * Created by flyex on 2018/10/23.
 */
public class NJieCheng {
    /**public void Go(long n){
        if(n==0){
            System.out.println(n+"的阶乘是:1");
        }else{
            long result = 1;
            for(int i =1;i<=n;i++){
                 result = result*i;
            }
            System.out.println(n+"的阶乘是："+result);
        }
    }*/

    public long Go(long n){
        if((n==0)||(n==1)){
            return 1;
        }else{
            return n*Go(n - 1);
        }
    }

    public static void main(String[] args){
        NJieCheng go = new NJieCheng();
        System.out.println(go.Go(0));
        System.out.println(go.Go(1));
        System.out.println(go.Go(2));
        System.out.println(go.Go(3));
        System.out.println(go.Go(4));
    }
}
