package Hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by flyex on 2018/10/13.
 */
public class ListTest {
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("xiao");
        list.add("si");
        list.add("ren");

        for(String tmp:list){
            System.out.println(tmp);
        }

        String[] str = new String[list.size()];
        list.toArray(str);
        for(int i =0;i<str.length;i++){
            System.out.println(str[i]);
        }

        Iterator<String> ite = list.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
        }
    }
}
