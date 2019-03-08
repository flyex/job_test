package Hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by flyex on 2018/10/13.
 */
public class MapTest {
    public static void main(String[] args){
        Map<String,String> map = new HashMap<String,String>();
        map.put("A","哈哈哈");
        map.put("C","旺旺网");
        map.put("B","喵喵喵");

        for(String key:map.keySet()){
            System.out.println(key+":"+map.get(key));
        }

        Iterator<Map.Entry<String,String>> ite = map.entrySet().iterator();
        while(ite.hasNext()){
            Map.Entry<String,String> entry = ite.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        for(Map.Entry<String,String> entry :map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
