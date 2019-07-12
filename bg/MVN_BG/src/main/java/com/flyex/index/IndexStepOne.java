package com.flyex.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;

public class IndexStepOne {
   public static class IndexSOMapper extends Mapper<LongWritable,Text,Text, IntWritable>{
       @Override
       protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
           FileSplit inputSplit = (FileSplit) context.getInputSplit();
           String fileName = inputSplit.getPath().getName();
           String[] words = value.toString().split(" ");

           for (String word:words){
               context.write(new Text(word+"-"+fileName),new IntWritable(1));
           }
       }
   }
   public static class IndexSOReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
       @Override
       protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
           int count = 0;
           Iterator<IntWritable> iterator = values.iterator();
           while (iterator.hasNext()){
               count += iterator.next().get();
           }
           context.write(key,new IntWritable(count));
       }
   }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(); // 默认只加载core-default.xml core-site.xml

        Job job = Job.getInstance(conf);

        job.setJarByClass(IndexStepOne.class);

        job.setMapperClass(IndexSOMapper.class);
        job.setReducerClass(IndexSOReducer.class);

        job.setNumReduceTasks(3);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\hdpTest\\wordcount\\input\\index_in"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hdpTest\\wordcount\\output\\index_out1"));

        job.waitForCompletion(true);
    }
}
