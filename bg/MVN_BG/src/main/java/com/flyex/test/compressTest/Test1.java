package com.flyex.test.compressTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;

public class Test1 {
    public static class TCompMapper extends Mapper<LongWritable, Text, NullWritable,Text>{
        Text text = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            text.set(line);
            context.write(NullWritable.get(),text);
        }
    }

    public static class TCompReducer extends Reducer<NullWritable,Text,NullWritable,Text>{
        Text text = new Text();
        @Override
        protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Iterator<Text> iterator = values.iterator();
            while (iterator.hasNext()){
                text = iterator.next();
                context.write(null,text);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();


        conf.set("mapreduce.output.fileoutputformat.compress","true");
        conf.set("mapreduce.output.fileoutputformat.compress.type","RECORD");
        conf.set("mapreduce.output.fileoutputformat.compress.codec","org.apache.hadoop.io.compress.BZip2Codec");

        Job job = Job.getInstance(conf);

        job.setJarByClass(Test1.class);


        job.setMapperClass(TCompMapper.class);
        job.setReducerClass(TCompReducer.class);

        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(1);


        FileInputFormat.setInputPaths(job,new Path("D:\\testData\\text"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\testDataOut\\compressTest"));

        job.waitForCompletion(true);


    }
}
