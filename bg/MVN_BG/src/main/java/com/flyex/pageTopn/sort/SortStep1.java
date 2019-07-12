package com.flyex.pageTopn.sort;

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

public class SortStep1 {
    public static class SortStep1Mapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(" ");
            context.write(new Text(words[1]),new IntWritable(1));
        }
    }
    public static class SortStep1Reducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value:values){
                count += value.get();
            }
            context.write(key,new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(SortStep1.class);

        job.setMapperClass(SortStep1Mapper.class);
        job.setReducerClass(SortStep1Reducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\hdpTest\\wordcount\\input\\input__"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hdpTest\\wordcount\\output\\output_sort"));

        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}
