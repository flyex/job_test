package Exception;

/**
 * Created by flyex on 2018/9/26.
 */
public class FuShuException extends Exception {
    private int value;
    FuShuException(){
        super();
    }
    FuShuException(String msg,int value){
        super(msg);
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
