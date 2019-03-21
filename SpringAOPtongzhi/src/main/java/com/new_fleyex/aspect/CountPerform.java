package com.new_fleyex.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CountPerform {
    private Map<Integer,Integer> counts = new HashMap<Integer, Integer>();

    @Before("execution(* com.new_fleyex.cut_point.Performance.perform(int))" +
            "&&args(j)")
    public void count(int j ){
        int currentCounts = getCount(j);
        counts.put(j,currentCounts+1);
    }

    public int getCount(int j){
        return counts.containsKey(j)?counts.get(j):0;
    }
}
