package Exception;

/**
 * Created by flyex on 2018/9/26.
 */
public class MyException extends Exception {
    private float value;
    MyException(){
        super();
    }
    MyException(String msg,float value){
        super(msg);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
