package com.jlyex;

import scala.math.Ordering;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        HashMap<String, Integer> count = new HashMap<String,Integer>();

        for (int i = 0;i<args.length;i++){
            if (count.get(args[i]) == null){
                count.put(args[i],1);
            }else {
                count.put(args[i],count.get(args[i])+1);
            }
        }
        //将map转换为一个map.EntrySet集合，在重写list比较器
        ArrayList<Map.Entry<String, Integer>> countList = new ArrayList<>(count.entrySet());
        Collections.sort(countList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });


        System.out.println(countList.toString());

        /*将排序的entryList转换为map
        LinkedHashMap会保留添加数据时的位置信息
         */
        count.clear();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        Iterator<Map.Entry<String, Integer>> iterator = countList.iterator();
        Map.Entry<String,Integer> map = null;
        while (iterator.hasNext()){
            map = iterator.next();
            sortedMap.put(map.getKey(),map.getValue());
        }

        //Iterator<Map.Entry<String, Integer>> iterator = count.entrySet().iterator();
        for (Map.Entry<String,Integer> entry:sortedMap.entrySet()){
            System.out.println(entry.getKey()+"|"+entry.getValue());
        }

    }
}
