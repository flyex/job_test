package Date;

/**
 * Created by flyex on 2018/9/17.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
    public static void main(String args[]) {
        // 初始化 Date 对象
        Date date = new Date();
        long time = date.getTime();
        Date date2 = new Date();
        date2.setTime(10000000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ww W F E");
        // 使用 toString() 函数显示日期时间
        System.out.println(date.toString());
        System.out.println(sdf.format(date));
        System.out.println(date2.toString());
    }
}