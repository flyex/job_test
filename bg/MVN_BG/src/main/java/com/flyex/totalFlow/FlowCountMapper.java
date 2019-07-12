package com.flyex.totalFlow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split("\t");
        String phone = words[1];
        int upflow = Integer.parseInt(words[words.length-3]);
        int dflow = Integer.parseInt(words[words.length-2]);

        context.write(new Text(phone),new FlowBean(phone,upflow,dflow));
    }
}
