package com.hbase.hbase2mysql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class H2MRunner {

    public static void main(String[] args) throws Exception {

        Configuration conf = HBaseConfiguration.create();

        Job job = Job.getInstance(conf);

        job.setJarByClass(H2MRunner.class);

        //input setting
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("cf1"));
        TableMapReduceUtil.initTableMapperJob(
                "test:stu2",
                scan,
                H2MMapper.class,
                Text.class,
                Text.class,
                job
        );

        job.setReducerClass(H2MReducer.class);

        job.setOutputFormatClass(MysqlOutput.class);


        job.waitForCompletion(true);


    }

}
