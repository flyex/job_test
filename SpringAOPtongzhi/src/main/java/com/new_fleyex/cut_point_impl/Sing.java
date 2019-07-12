package com.new_fleyex.cut_point_impl;

import com.new_fleyex.cut_point.Performance;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class Sing implements Performance {
    List<String> catalog = new ArrayList<>();

    public void setCatalog(List list){
        catalog = list;
    }

    @Override
    public void perform(int i) {
        System.out.println("i am sing a song:"+catalog.get(i));
    }
}
