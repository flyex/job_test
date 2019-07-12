package com.flyex.index;

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

public class IndexStepTwo {
    public static class IndexSTMapper extends Mapper<LongWritable, Text,Text,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("-");
            context.write(new Text(split[0]),new Text(split[1].replaceAll("\t","-->")));
        }
    }
    public static class IndexSTReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();
            for (Text value:values){
                sb.append(value.toString()).append("\t");
            }
            context.write(key,new Text(sb.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(); // 默认只加载core-default.xml core-site.xml

        Job job = Job.getInstance(conf);

        job.setJarByClass(IndexStepTwo.class);

        job.setMapperClass(IndexSTMapper.class);
        job.setReducerClass(IndexSTReducer.class);

        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\hdpTest\\wordcount\\output\\index_out1"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hdpTest\\wordcount\\output\\index_fina"));

        job.waitForCompletion(true);
    }
}
