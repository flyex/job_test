package Exception;

/**
 * Created by flyex on 2018/9/26.
 */
public class MyTest {
    float test(float a,float b) throws MyException{
        if(b == 0){
            throw new MyException("除数不能是0",b);
        }
        return a/b;
    }
}
