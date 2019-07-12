package com.flyex.friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FriendHave2 {
    public static class FriendMapper2 extends Mapper<LongWritable,Text,Text,Text>{
        Text k = new Text();
        Text v = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] s = value.toString().split("\t");
            k.set(s[0]);
            v.set(s[1]);
            context.write(k,v);
        }
    }
    public static class FriendReducer2 extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            ArrayList<String> arrayList = new ArrayList<String>();
            for (Text t:values){
                arrayList.add(t.toString());
            }

            Collections.sort(arrayList);
            context.write(key,new Text(arrayList.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FriendHave2.class);
        job.setMapperClass(FriendMapper2.class);
        job.setReducerClass(FriendReducer2.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\output\\output_friend"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_finafriend"));

        job.waitForCompletion(true);
    }
}
