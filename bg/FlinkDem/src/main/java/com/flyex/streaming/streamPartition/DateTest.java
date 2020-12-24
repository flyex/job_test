package com.flyex.streaming.streamPartition;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateTest {

    public static void main(String[] args) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        date.setTime(1579135230000L);
        //System.out.println(sdf.format(date));

        Random random = new Random();

        while (true){
            int i = random.nextInt(3);
            System.out.println(i);
        }

    }
}
