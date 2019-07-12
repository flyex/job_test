package Collections;

import java.util.*;

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

        Set set = new HashSet();
        set.add(1);
        set.add("1");
        set.add(2);
        set.add(1);
        set.add(null);
        System.out.println(set);

        List list2 = new ArrayList();
        list2.add(null);
        list2.add(1);
        list2.add(1);
        list2.add("1");
        System.out.println(list2);
        System.out.println(list2.get(3).getClass());

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push("啊哈");

        System.out.println(stack);

        List list3 = new ArrayList();
        List list4 = new ArrayList();

        list3.add(0,1);
        list3.add(0,2);
        list3.add(0,3);
        list4.add(1);
        list4.add(2);
        list4.add(3);
        System.out.println(list3+"对比"+list4);
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("12","test");
        hashMap.put("13","test13");
        hashMap.put("12","test12");

        for (Map.Entry<String,String> map:hashMap.entrySet()){
            System.out.println(map.getKey()+"|"+map.getValue());
        }
    }
}
