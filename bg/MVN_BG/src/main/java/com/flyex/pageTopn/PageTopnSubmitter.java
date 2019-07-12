package com.flyex.pageTopn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PageTopnSubmitter {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.addResource("topn.xml");

        Job job = Job.getInstance(conf);

        job.setJarByClass(PageTopnSubmitter.class);

        job.setMapperClass(PageTopnMapper.class);
        job.setReducerClass(PageTopnReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\input__"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output__"));


        job.waitForCompletion(true);
    }
}
