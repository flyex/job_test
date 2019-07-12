package com.flyex.text;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class TestPartitioner extends Partitioner<TextBean, NullWritable> {
    @Override
    public int getPartition(TextBean textBean, NullWritable nullWritable, int i) {
        return textBean.getNum()%2;
    }
}
