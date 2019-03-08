package Thread;

import java.util.concurrent.Callable;

/**
 * Created by flyex on 2018/10/18.
 */
public class MyCallable implements Callable<Integer> {
    int x;
    int y;
    public MyCallable(){}
    public MyCallable(int x, int y){
        this.x  = x;
        this.y = y;
    }
    @Override
    public Integer call() throws Exception{
        return x+y;
    }
}
