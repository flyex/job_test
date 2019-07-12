package com.flyex.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class JobSubmiiter {
    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME","root");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://hdp-01:9000");
        conf.set("mapreduce.framework.name","yarn");
        conf.set("yarn.resourcemanager.hostname","hdp-01");

        conf.set("mapreduce.app-submission.cross-platform","true");

        Job job = Job.getInstance(conf);
        job.setJar("D:\\project\\bg\\MVN_BG\\target\\MVN_BG-1.0-SNAPSHOT.jar");

        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path output = new Path("/wordcount/output");
        FileSystem fs = FileSystem.get(new URI("hdfs://hdp-01:9000"), conf, "root");

        if (fs.exists(output)){
            fs.delete(output,true);
        }

        FileInputFormat.setInputPaths(job,new Path("/wordcount/input"));
        FileOutputFormat.setOutputPath(job,output);

        job.setNumReduceTasks(2);

        boolean res = job.waitForCompletion(true);

        System.exit(res?0:-1);
    }
}
