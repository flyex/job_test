package forWhileDo;

/**
 * Created by flyex on 2018/9/11.
 */
public class JiuJiu {
    public static void main(String[] args){
        for(int i = 1;i<10;i++){
            for(int j =1;j<=i;j++){
                System.out.print(j+"*"+ i + "=" + i*j+"     ");
            }
            System.out.println( );
        }

        int k=1;
        k = k<<1;
        System.out.println(k);
    }
}
