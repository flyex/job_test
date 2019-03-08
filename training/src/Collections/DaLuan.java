package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by flyex on 2018/10/29.
 */
public class DaLuan {
    public static void main(String[] args){
        List<Long> list = new ArrayList<>();

        list.add(new Long(1));
        list.add(new Long(2));
        list.add(new Long(3));

        Collections.shuffle(list);
        System.out.println(list);
    }
}
