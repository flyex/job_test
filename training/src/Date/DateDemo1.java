package Date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by flyex on 2018/9/17.
 */
public class DateDemo1 {
    public static void main(String[] args){
        Date newDate = new Date();
        Date newDate1 = new Date();
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println(ft.format(newDate));
        System.out.println(ft1.format(newDate1));
    }
}
