package com.flyex.demo.hbase.pro;

/*
map处理后存入hbase中
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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
import java.util.UUID;

public class Cc2Hbase {

    public static class CcMapper extends Mapper<LongWritable, Text, NullWritable, Put>{

//        @Override
//        protected void setup(Context context) throws IOException, InterruptedException {
//            super.setup(context);
//        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String[] fields = line.split(",", -1);
            String name = fields[0];
            int age = Integer.valueOf(fields[1]);
            String sex = fields[2];

            //往Hbase里写数据
            //创建一个put对象 包括rowkey ，family，kv

            byte[] rowKey_ = Bytes.toBytes(UUID.randomUUID().toString().substring(0,8) + name.hashCode());

            Put put = new Put(rowKey_);

            put.addColumn(Bytes.toBytes("base_info"),Bytes.toBytes("name"),Bytes.toBytes(name));
            put.addColumn(Bytes.toBytes("base_info"),Bytes.toBytes("age"),Bytes.toBytes(age));
            put.addColumn(Bytes.toBytes("base_info"),Bytes.toBytes("sex"),Bytes.toBytes(sex));

            context.write(NullWritable.get(),put);

        }



        public static void main(String[] args) throws Exception{

            Configuration conf = new Configuration();

            Job job = Job.getInstance(conf);
            job.setJarByClass(Cc2Hbase.class);

            job.setMapperClass(CcMapper.class);
            job.setMapOutputKeyClass(NullWritable.class);
            job.setOutputValueClass(Put.class);

            job.setNumReduceTasks(0);
            FileInputFormat.setInputPaths(job,new Path("hdfs://ns-ha/web_log/studentDir"));

            //初始化Hbase配置
            TableMapReduceUtil.initTableReducerJob("student",null,job);

            job.waitForCompletion(true);



        }
    }

}
