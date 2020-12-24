package com.flyex.WC;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Practice {

    public static class Mapper_a extends Mapper<LongWritable, Text,Text, IntWritable>{

        Text text = new Text();
        IntWritable v = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(" ");
            for (String word:words){
                text.set(word);
                context.write(text,v);
            }
        }
    }

    public static class reduce_a extends Reducer<Text,IntWritable,Text,IntWritable>{

        IntWritable i = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            int count = 0;
            Iterator<IntWritable> iterator = values.iterator();
            while (iterator.hasNext()){
                IntWritable next = iterator.next();
                count += next.get();
            }

            i.set(count);
            context.write(key,i);
        }
    }

    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(Practice.class);

        job.setMapperClass(Mapper_a.class);
        job.setReducerClass(reduce_a.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\testData\\wc"));

        File file = new File("D:\\testDataOut\\wcOut");
        if (file.exists()){
            FileUtils.deleteQuietly(file);
        }

        FileOutputFormat.setOutputPath(job,new Path("D:\\testDataOut\\wcOut"));

        job.setNumReduceTasks(1);

        job.waitForCompletion(true);

    }
}
