package com.flyex.streaming.streamPartition;

import org.apache.flink.api.common.functions.Partitioner;

public class MyPartition implements Partitioner<Long> {

    @Override
    public int partition(Long key, int numPartitions) {
        System.out.println("总分区数为："+ numPartitions);
        Long id = (Long) key%numPartitions;
        return  id.intValue();
    }
}
