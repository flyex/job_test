package Exception;

/**
 * Created by flyex on 2018/9/26.
 */
public class Demo {
    int go(int a ,int b)throws FuShuException{
        if(b<0){
            throw new FuShuException(("出现除数是负数"),b);
        }
        return a/b;
    }
}
