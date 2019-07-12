package com.my.test_baiducount;

import com.flyex.totalFlow.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.HashMap;

public class Baidu {
    public static class BaiduMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] line = value.toString().split(" ");
            context.write(new Text(line[1]),new IntWritable(1));
        }
    }
    public static class BaiPartitioner extends Partitioner<Text,IntWritable>{
        static HashMap<String,Integer> code = new HashMap<String, Integer>();
        static {
            code.put("movie",0);
        }
        @Override
        public int getPartition(Text text, IntWritable intWritable, int i) {
            String[] word = text.toString().split("\\.|/");
            for (int j = 0;j<word.length;j++){
                if (word[j].equals("movie")){
                    return 0;
                }
            }
            return 1;
        }
    }
    public static class BaiduReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable i:values){
                count += i.get();
            }
            context.write(key,new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Baidu.class);

        job.setPartitionerClass(BaiPartitioner.class);
        job.setMapperClass(BaiduMapper.class);
        job.setReducerClass(BaiduReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\input__"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_baidumovie"));

        job.waitForCompletion(true);

    }
}
