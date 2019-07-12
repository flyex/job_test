package com.flyex.groupOrder.nb;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class OrderPartitioner extends Partitioner<OrderBean2, NullWritable> {
    @Override
    public int getPartition(OrderBean2 orderBean2, NullWritable nullWritable, int i) {
        return (orderBean2.getOrderId().hashCode() & Integer.MAX_VALUE)%i;
    }
}
