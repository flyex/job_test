package com.new_fleyex.aspect;


import java.util.HashMap;
import java.util.Map;

public class CountPerform_xml {
    private Map<Integer,Integer> counts = new HashMap<Integer, Integer>();


    public void count(int j ){
        int currentCounts = getCount(j);
        counts.put(j,currentCounts+1);
    }

    public int getCount(int j){
        return counts.containsKey(j)?counts.get(j):0;
    }
}