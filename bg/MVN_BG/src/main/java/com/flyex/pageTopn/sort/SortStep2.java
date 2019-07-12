package com.flyex.pageTopn.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SortStep2 {
    public static class SortStep2Mapper extends Mapper<LongWritable,Text,PageCount, NullWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split("\t");
            PageCount pageCount = new PageCount();
            pageCount.set(words[0],Integer.parseInt(words[1]));
            context.write(pageCount,NullWritable.get());
        }
    }
    public static class SortStep2Reducer extends Reducer<PageCount,NullWritable,PageCount,NullWritable>{
        @Override
        protected void reduce(PageCount key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key,NullWritable.get());
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(SortStep2.class);

        job.setMapperClass(SortStep2Mapper.class);
        job.setReducerClass(SortStep2Reducer.class);

        job.setMapOutputKeyClass(PageCount.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(PageCount.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\output\\output_sort"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_finalsort"));

        job.setNumReduceTasks(1);

        job.waitForCompletion(true);
    }
}
