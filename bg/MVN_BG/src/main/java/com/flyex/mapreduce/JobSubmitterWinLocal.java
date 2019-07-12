package com.flyex.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobSubmitterWinLocal {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(JobSubmitterWinLocal.class);

        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output"));

        job.setNumReduceTasks(2);

        boolean res = job.waitForCompletion(true);
        System.exit(res?0:-1);
    }
}
