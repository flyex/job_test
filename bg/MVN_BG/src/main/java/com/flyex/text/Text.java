package com.flyex.text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.LinkedList;

public class Text {
    public static class TMapper extends Mapper<LongWritable, org.apache.hadoop.io.Text,TextBean, NullWritable>{
        TextBean bean = new TextBean();
        @Override
        protected void map(LongWritable key, org.apache.hadoop.io.Text value, Context context) throws IOException, InterruptedException {
            int k = Integer.parseInt(value.toString());
            bean.setNum(k);
            context.write(bean,NullWritable.get());
        }
    }
    public static class TReducer extends Reducer<TextBean,NullWritable,TextBean,NullWritable>{

        @Override
        protected void reduce(TextBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            int i = 0;
            for (NullWritable v:values){
                context.write(key,v);
                if (++i==2) return;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        new LinkedList<>();

        job.setJarByClass(Text.class);
        job.setMapperClass(TMapper.class);
        job.setReducerClass(TReducer.class);

        job.setGroupingComparatorClass(TestComparator.class);
        job.setPartitionerClass(TestPartitioner.class);
        job.setMapOutputKeyClass(TextBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(TextBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\text"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\text"));

        job.waitForCompletion(true);
    }
}
