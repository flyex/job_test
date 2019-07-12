package com.my.computer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CompSub {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(CompSub.class);

        job.setMapperClass(ComputerMapper.class);
        job.setReducerClass(ComputerReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\input_computer"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_computer"));

        job.waitForCompletion(true);
    }
}
