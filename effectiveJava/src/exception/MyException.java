package exception;

import java.util.Map;

public class MyException extends Exception {
    public MyException(int x){

            super("水烧开了," + x + "度了");

    }
}
