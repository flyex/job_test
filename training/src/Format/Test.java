package Format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by flyex on 2018/10/22.
 */
public class Test {
    public static void main(String[] args){
        Date date = new Date();
        String format = "yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        System.out.println(sdf.format(date));
    }
}
