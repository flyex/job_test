package com.my.computer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Sort {
    public static class Mp extends Mapper<LongWritable, Text, IntWritable,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] line = value.toString().split("\t");
            context.write(new IntWritable(Integer.parseInt(line[1])),new Text(line[0]));
        }
    }
    public static class Rd extends Reducer<IntWritable,Text,IntWritable,Text>{
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for (Text value:values){
                sb.append(value.toString()).append("\t");
            }
            context.write(key,new Text(sb.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(Sort.class);

        job.setMapperClass(Mp.class);
        job.setReducerClass(Rd.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\output\\output_computer"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_finacomputer"));

        job.waitForCompletion(true);
    }
}
