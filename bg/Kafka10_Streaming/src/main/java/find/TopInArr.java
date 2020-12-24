package find;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TopInArr {

    public static void getTopN(ArrayList<String> arr){

        HashMap<Long, String> data = new HashMap<>();
        long i = 0L;
        for(String s : arr){
            data.put(i++,s);
        }

        ArrayList<Map.Entry<Long, String>> entries = new ArrayList<>(data.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Long, String>>() {
            @Override
            public int compare(Map.Entry<Long, String> o1, Map.Entry<Long, String> o2) {
                return o2.getValue().length()-o1.getValue().length();
            }
        });

        for (int j = 0; j < 4; j++) {
            System.out.print(entries.get(j).getValue()+"\t");
        }


    }
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("apple");
        arrayList.add("banana");
        arrayList.add("apple");
        arrayList.add("a1pple");
        arrayList.add("a1p2ple");
        arrayList.add("a1p2p3l4e");


        getTopN(arrayList);
        //System.out.println(arrayList.toString());

    }
}
