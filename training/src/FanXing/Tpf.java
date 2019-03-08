package FanXing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyex on 2018/10/13.
 */
public class Tpf {
    public void getData(List<?> list){
        for(int i = 0;i<list.size();i++){
            System.out.print(list.get(i)+"   ");
        }
        System.out.println(" ");
    }

    //public void getType(List<? extends Integer> list)
    public void getType(List<? extends Integer> list){
        for(int i = 0;i<list.size();i++){
            System.out.print(list.get(i)+"   ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args){
        List<Integer> inte = new ArrayList<Integer>();
        List<String> str = new ArrayList<String>();
        List<Number> num = new ArrayList<Number>();

        inte.add(10);
        inte.add(20);
        str.add("666");
        str.add("哈哈哈");
        num.add(12);
        num.add(13);

        Tpf t = new Tpf();
        t.getData(inte);
        //t.getData(str);
        //t.getData(num);
        //t.getType(num);
        t.getType(inte);
    }
}
