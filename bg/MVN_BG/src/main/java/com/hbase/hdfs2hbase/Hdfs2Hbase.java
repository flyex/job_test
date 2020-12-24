package com.hbase.hdfs2hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class Hdfs2Hbase {

    public static class toHbaseMapper extends Mapper<LongWritable, Text, NullWritable, Put> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split(" ", -1);
            Put put = new Put(Bytes.toBytes(fields[0]));
            put.add(Bytes.toBytes("cf1"),Bytes.toBytes("name"),Bytes.toBytes(fields[1]));

            context.write(NullWritable.get(),put);
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conff = new Configuration();
        Configuration conf = HBaseConfiguration.create(conff);
        Job job = Job.getInstance(conf);

        job.setJarByClass(Hdfs2Hbase.class);

        job.setMapperClass(toHbaseMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Put.class);
        FileInputFormat.addInputPath(job, new Path("/inputData/toHbase"));

        job.setNumReduceTasks(0);

        initHbaseOutputConfig(job);

        job.waitForCompletion(true);


    }

    private static void initHbaseOutputConfig(Job job) throws IOException {

        TableMapReduceUtil.initTableReducerJob("test:stu2",null,job);

    }


}
