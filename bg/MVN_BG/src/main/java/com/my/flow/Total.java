package com.my.flow;

import com.my.test_baiducount.Baidu;
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

public class Total {
    public static class FlowMapper extends Mapper<LongWritable, Text,FlowBean, NullWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split("\t");
            String phone = words[1];
            int upf = Integer.parseInt(words[words.length-3]);
            int df = Integer.parseInt(words[words.length-2]);
            int totalf = upf+df;
            FlowBean flowBean = new FlowBean(phone,upf,df,totalf);
            context.write(flowBean,NullWritable.get());
        }
    }
    public static class FlowReducer extends Reducer<FlowBean,NullWritable,FlowBean,NullWritable>{
        @Override
        protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key,NullWritable.get());
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Total.class);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(FlowBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\input_"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_myflow"));

        job.waitForCompletion(true);

    }
}
