package com.flyex.groupOrder.nb;

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

public class Order2Topn {
    public static class Order2Mapper extends Mapper<LongWritable, Text,OrderBean2, NullWritable>{
        OrderBean2 orderBean2 = new OrderBean2();
        NullWritable v = NullWritable.get();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] words = value.toString().split(",");
            orderBean2.set(words[0],words[1],words[2],Float.parseFloat(words[3]),Integer.parseInt(words[4]));
            context.write(orderBean2,v);
        }
    }
    public static class  Order2Reducer extends Reducer<OrderBean2,NullWritable,OrderBean2,NullWritable>{
        @Override
        protected void reduce(OrderBean2 key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            int i = 0;
            for (NullWritable v:values){
                context.write(key,v);
                if (++i==3) return;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration(); // 默认只加载core-default.xml core-site.xml
        conf.setInt("order.top.n", 2);

        Job job = Job.getInstance(conf);

        job.setJarByClass(Order2Topn.class);

        job.setMapperClass(Order2Mapper.class);
        job.setReducerClass(Order2Reducer.class);

        job.setPartitionerClass(OrderPartitioner.class);
        job.setGroupingComparatorClass(OrderGroupComparator.class);

        job.setNumReduceTasks(14);

        job.setMapOutputKeyClass(OrderBean2.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean2.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\hdpTest\\wordcount\\input\\input___"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hdpTest\\wordcount\\output\\output___group"));

        job.waitForCompletion(true);
    }
}
