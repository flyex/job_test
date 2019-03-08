package Format;

import java.util.Calendar;

/**
 * Created by flyex on 2018/10/22.
 */
public class Ymd {
    public static void main(String[] args){
        Calendar cal = Calendar.getInstance();
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        int day2 = cal.get(Calendar.DAY_OF_WEEK);
        int day3 = cal.get(Calendar.DAY_OF_YEAR);
        int day4 = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        System.out.println("月中天"+day1+"周中天"+day2+"年中天"+day3+"  "+day4);
    }
}
